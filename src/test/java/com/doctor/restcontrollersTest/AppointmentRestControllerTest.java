package com.doctor.restcontrollersTest;

import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.requestDto.HospitalAddressRequestDto;
import com.doctor.restcontrollers.AppointmentRestController;
import com.doctor.services.IAppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AppointmentRestControllerTest {

    @Mock
    private IAppointmentService appointmentService;

    @InjectMocks
    private AppointmentRestController appointmentRestController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentRestController).build();
    }

    @Test
    void saveAppointment_Success() throws Exception {
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .appointmentDate(new Date())
                .doctorEmail("doctor@gmail.com")
                .doctorName("Doctor")
                .patientEmail("patient@gmail.com")
                .patientName("Patient")
                .patientMobileNo("9876543210")
                .time("11 A.M.")
                .build();

        Mockito.when(this.appointmentService.saveAppointment(Mockito.any(AppointmentRequestDto.class))).thenReturn(appointmentRequestDto);

        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v3/hms/appointment/saveAppointment")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(servletRequestBuilder)
                .andExpect(jsonPath("$.patientEmail").value("patient@gmail.com"));

    }

//    @Test
//    void updateAppointmentByAppointmentId_Success() throws Exception {
//        Long appointmentId = 11L;
//            AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
//                    .appointmentDate(new Date())
//                    .doctorEmail("doctor@gmail.com")
//                    .doctorName("Doctor")
//                    .patientEmail("patient@gmail.com")
//                    .patientName("Patient")
//                    .patientMobileNo("9876543210")
//                    .time("11 A.M.")
//                    .build();
//
//            Mockito.when(this.appointmentService.updateAppointment(eq(appointmentId), Mockito.any(AppointmentRequestDto.class))).thenReturn(appointmentRequestDto);
//
//            MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/api/v3/hms/appointment/updateAppointment/{appointmentId}", appointmentId)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .content(appointmentRequestDto.toString());
//
//            mockMvc.perform(mockHttpServletRequestBuilder)
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.doctorEmail").value("doctor@gmail.com"))
//                    .andExpect(jsonPath("$.doctorName").value("Doctor"))
//                    .andExpect(jsonPath("$.patientEmail").value("patient@gmail.com"))
//                    .andExpect(jsonPath("$.patientName").value("Patient"))
//                    .andExpect(jsonPath("$.time").value("11 A.M."))
//                    .andExpect(jsonPath("$.appointmentId").value(11L))
//                    .andDo(print());
//
//    }

    @Test
    void updateAppointmentByAppointmentId_Success() throws Exception {
        Long appointmentId = 11L;

        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .appointmentDate(new Date())
                .doctorEmail("doctor@gmail.com")
                .doctorName("Doctor")
                .patientEmail("patient@gmail.com")
                .patientName("Patient")
                .patientMobileNo("9876543210")
                .time("11:00")
                .build();

        Mockito.when(this.appointmentService.updateAppointment(appointmentId, appointmentRequestDto))
                .thenReturn(appointmentRequestDto);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String requestJson = objectMapper.writeValueAsString(appointmentRequestDto);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
                .put("/api/v3/hms/appointment/updateAppointment/{appointmentId}", appointmentId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.appointmentId").value(11L))
//                .andExpect(jsonPath("$.appointmentDate").value(new Date()))
                .andExpect(jsonPath("$.doctorEmail").value("doctor@gmail.com"))
                .andExpect(jsonPath("$.doctorName").value("Doctor"))
                .andExpect(jsonPath("$.patientEmail").value("patient@gmail.com"))
                .andExpect(jsonPath("$.patientName").value("Patient"))
                .andExpect(jsonPath("$.patientMobileNo").value("9876543210"))
                .andExpect(jsonPath("$.time").value("11:00"))
                .andDo(print());
    }

    @Test
    void getAppointmentByAppointmentEmail() throws Exception {

        String appointmentEmail = "doctor@gmail.com";
        List<AppointmentRequestDto> requestDto = new ArrayList<>();
        requestDto.add(AppointmentRequestDto.builder()
                .appointmentDate(new Date())
                .doctorEmail("doctor@gmail.com")
                .doctorName("Doctor")
                .patientEmail("patient@gmail.com")
                .patientName("Patient")
                .time("11:00")
                .build());
        requestDto.add(AppointmentRequestDto.builder()
                .appointmentDate(new Date())
                .doctorEmail("doctor1@gmail.com")
                .doctorName("Doctor1")
                .patientEmail("patient1@gmail.com")
                .patientName("Patient1")
                .time("11:00")
                .build());
        requestDto.add(AppointmentRequestDto.builder()
                .appointmentDate(new Date())
                .doctorEmail("doctor2@gmail.com")
                .doctorName("Doctor2")
                .patientEmail("patient2@gmail.com")
                .patientName("Patient2")
                .time("11:00")
                .build());

        Mockito.when(this.appointmentService.findByEmail(appointmentEmail))
                .thenReturn(requestDto);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v3/hms/appointment/getAppointment/{byPatientEmail}", appointmentEmail)
                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientEmail[0]").value("patient@gmail.com"))
                .andExpect(jsonPath("$.patientName[0]").value("Patient"))
                .andExpect(jsonPath("$.appointmentTime[0]").value("11:00"))
                .andExpect(jsonPath("$.doctorEmail[0]").value("doctor@gmail.com"))
                .andExpect(jsonPath("$.doctorName[0]").value("Doctor"))
                .andDo(print());
    }


}
