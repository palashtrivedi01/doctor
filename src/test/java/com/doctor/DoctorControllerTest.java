package com.doctor;

import com.doctor.controller.DoctorController;
import com.doctor.dto.DoctorRequestDto;
import com.doctor.entities.Doctor;
import com.doctor.enums.Role;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.service.DoctorServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.print.Doc;
import java.util.*;

import static org.awaitility.Awaitility.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {

    @Mock
    MockMvc mockMvc;

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorServices doctorServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }
//    @GetMapping("alldoctor")
//    public ResponseEntity<List<Doctor>> getAllDoctors() {
//        List<Doctor> doctors = doctorServices.getAllDoctors();
//        return new ResponseEntity<>(doctors, HttpStatus.OK);
//    }

    @Test
    public void getAllDoctorsSuccess() throws  Exception{
        List<Doctor> doctor = new ArrayList<>();
        Doctor doctor1 = Doctor.builder()
                .doctorId(1L)
                .role("Physician")
                .doctorEmail("john.doe@example.com")
                .doctorGender("Male")
                .doctorMobileNumber("1234567890")
                .doctorName("John Doe")
                .doctorPassword("password123")
                .doctorSpecialization("Cardiology")
                .registerDate(new Date())
                .build();
        doctor.add(doctor1);

        when(doctorServices.getAllDoctors()).thenReturn(doctor);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/doctor/alldoctor")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].doctorId").value(1L));
    }

    @Test
    public void deleteByIdSuccess() throws Exception {
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorMobileNumber("3467778");
        doctorRequestDto.setDoctorSpecialization("Doctor");
        doctorRequestDto.setDoctorPassword("demo");
        doctorRequestDto.setDoctorName("Ram");
        doctorRequestDto.setDoctorGender("male");
        doctorRequestDto.setDoctorEmail("Ram@gmail.com");
        doNothing().when(doctorServices).deleteDoctorById(anyLong());
        doctorServices.deleteDoctorById(4L);
        verify(doctorServices, times(1)).deleteDoctorById(anyLong());
    }

    @Test
    public void addDoctorSuccess() throws Exception {
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorMobileNumber("3467778");
        doctorRequestDto.setDoctorSpecialization("Doctor");
        doctorRequestDto.setDoctorPassword("demo");
        doctorRequestDto.setDoctorName("Ram");
        doctorRequestDto.setDoctorGender("male");
        doctorRequestDto.setDoctorEmail("Ram@gmail.com");

        Doctor doctor1 = Doctor.builder()
                .doctorSpecialization("demo").doctorName("payal").doctorGender("female")
                .resetPasswordToken("abc").build();
        when(doctorServices.addDoctor(doctorRequestDto)).thenReturn(doctor1);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(doctorRequestDto);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/doctor/adddoctor", doctorRequestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.doctorName").value("payal"))
                .andExpect(jsonPath("$.doctorSpecialization").value("demo"))
                .andExpect(jsonPath("$.doctorGender").value("female"))
                .andDo(MockMvcResultHandlers.print());

        verify(doctorServices, times(1)).addDoctor(doctorRequestDto);
    }

    @Test
    public void getDoctorByIdSuccess() throws Exception {
        Doctor doctor = new Doctor();
        int doctorId = 1;
        ObjectMapper objectMapper = new ObjectMapper();

        // when(doctorServices.getDoctorById(doctorId)).thenReturn(Optional.of(doctor));
        doReturn(Optional.of(doctor)).when(doctorServices).getDoctorById(1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/doctor/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
        // verify(doctorServices, times(1)).getAllDoctors();
    }

    @Test
    public void updateDoctorSuccess() throws Exception {
        String doctorEmail = "Ram@gmail.com";
        ObjectMapper objectMapper = new ObjectMapper();

        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorEmail(doctorEmail);
        doctorRequestDto.setDoctorName("Ram");
        doctorRequestDto.setDoctorGender("male");
        doctorRequestDto.setDoctorMobileNumber("3467778");
        doctorRequestDto.setDoctorSpecialization("Cardiology");
        doctorRequestDto.setDoctorPassword("newPassword");

        Doctor doctor1 = Doctor.builder()
                .doctorSpecialization("demo").doctorName("payal").doctorGender("female")
                .resetPasswordToken("abc").build();
        when(doctorServices.updateDoctor(doctorEmail, doctorRequestDto)).thenReturn(doctor1);

        String requestBody = objectMapper.writeValueAsString(doctorRequestDto);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/doctor/updateDoctor/{doctorEmail}", doctorEmail)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.doctorName").value("payal"))
                .andExpect(jsonPath("$.doctorSpecialization").value("demo"))
                .andExpect(jsonPath("$.doctorGender").value("female"))
                .andDo(MockMvcResultHandlers.print());


    }
}