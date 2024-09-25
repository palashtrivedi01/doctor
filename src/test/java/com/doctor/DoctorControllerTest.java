package com.doctor;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.doctor.controller.DoctorController;
import com.doctor.requestdto.DoctorRequestDto;
import com.doctor.services.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class DoctorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void AddDoctor() throws Exception {
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorName("hi");
        doctorRequestDto.setDoctorGender("male");
        doctorRequestDto.setDoctorSpecialization("MD");
        doctorRequestDto.setDoctorPassword("1234");
        doctorRequestDto.setDoctorEmail("doctor@email.com");
        doctorRequestDto.setDoctorMobileNumber(64646464L);
        when(doctorService.addDoctor(any(DoctorRequestDto.class))).thenReturn(doctorRequestDto);
        mockMvc.perform(post("/doctor/addDoctor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(doctorRequestDto)));
        verify(doctorService, times(1)).addDoctor(any(DoctorRequestDto.class));
    }

    @Test
    public void updateDoctorTest() throws Exception {
        String doctorEmail = "doctor@email.com";
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorName("hi");
        doctorRequestDto.setDoctorGender("male");
        doctorRequestDto.setDoctorSpecialization("MD");
        doctorRequestDto.setDoctorPassword("1234");
        doctorRequestDto.setDoctorMobileNumber(64646464L);
        when(doctorService.updateDoctor(any(DoctorRequestDto.class), eq(doctorEmail))).thenReturn(doctorRequestDto);
        mockMvc.perform(put("/doctor/updateDoctor/{doctorEmail}", doctorEmail)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(doctorRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(doctorRequestDto)));
        verify(doctorService, times(1)).updateDoctor(any(DoctorRequestDto.class), eq(doctorEmail));
    }

    @Test
    public void getDoctorByDoctorEmailTest() throws Exception {
        String doctorEmail = "doctor@email.com";
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorEmail("doctor@email.com");
        doctorRequestDto.setDoctorName("Dr. John Doe");
        doctorRequestDto.setDoctorGender("male");
        doctorRequestDto.setDoctorSpecialization("MD");
        doctorRequestDto.setDoctorPassword("password");
        doctorRequestDto.setDoctorMobileNumber(64646464L);
        when(doctorService.getDoctorByDoctorEmail(doctorEmail)).thenReturn(doctorRequestDto);

        mockMvc.perform(get("/doctor/doctorEmail/{doctorEmail}", doctorEmail)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.doctorEmail")).value(doctorEmail))
                .andExpect(content().json(objectMapper.writeValueAsString(doctorRequestDto)));
        verify(doctorService, times(1)).getDoctorByDoctorEmail(doctorEmail);
    }

    @Test
    public void getDoctorByIdTest() throws Exception {
        Long doctorId = 1L;
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorEmail("doctor@email.com");
        doctorRequestDto.setDoctorId(1L);
        doctorRequestDto.setDoctorName("Dr. John Doe");
        doctorRequestDto.setDoctorGender("male");
        doctorRequestDto.setDoctorSpecialization("MD");
        doctorRequestDto.setDoctorPassword("password");
        doctorRequestDto.setDoctorMobileNumber(64646464L);
        when(doctorService.getDoctorByDoctorId(doctorId)).thenReturn(doctorRequestDto);

        mockMvc.perform(get("/doctor/doctorId/{doctorId}", doctorId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.doctorId")).value(doctorId))
                .andExpect(content().json(objectMapper.writeValueAsString(doctorRequestDto)));
        verify(doctorService, times(1)).getDoctorByDoctorId(doctorId);

    }

    @Test
    public void getAllDoctorsTest() throws Exception {
        DoctorRequestDto dto1 = new DoctorRequestDto();
        dto1.setDoctorEmail("doctor@email.com");
        dto1.setDoctorName("Dr. John Doe");
        dto1.setDoctorGender("male");
        dto1.setDoctorSpecialization("MD");

        DoctorRequestDto dto2 = new DoctorRequestDto();
        dto2.setDoctorEmail("wick@gmail.com");
        dto2.setDoctorName("Dr. wick Doe");
        dto2.setDoctorGender("male");
        dto2.setDoctorSpecialization("MD");

        DoctorRequestDto dto3 = new DoctorRequestDto();
        dto3.setDoctorEmail("james@gmail.com");
        dto3.setDoctorName("Dr. james bond");
        dto3.setDoctorGender("female");
        dto3.setDoctorSpecialization("MBBS");
        List<DoctorRequestDto> doctorRequestDtoList = new ArrayList<>();
        doctorRequestDtoList.add(dto1);
        doctorRequestDtoList.add(dto2);
        doctorRequestDtoList.add(dto3);
        when(doctorService.getAllDoctors()).thenReturn(doctorRequestDtoList);
        mockMvc.perform(get("/doctor/allDoctor")
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(doctorRequestDtoList)));
        verify(doctorService, times(1)).getAllDoctors();


    }








}
