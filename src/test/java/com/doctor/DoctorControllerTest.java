package com.doctor;



import com.doctor.controller.DoctorController;
import com.doctor.dto.DoctorRequestDTO;
import com.doctor.service.serviceimpl.HelloDoctorServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {

    @InjectMocks
    DoctorController doctorController;

    @Mock
    HelloDoctorServices helloDoctorServices;

    @Mock
    MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(DoctorController.class).build();
    }

    @Test
    void addDoctor_Success() throws Exception {
      //  ObjectMapper objectMapper = new ObjectMapper();
        //  DoctorRequestDTO doctorRequestDTO = new DoctorRequestDTO("Azmi@gmail.com","Azmi@gmail.com","Azmi@gmail.com");
        DoctorRequestDTO doctorRequestDTO = new DoctorRequestDTO(
                Role.ROLE_DOCTOR,                        // role
                "Azmi@gmail.com",                   // doctorEmail
                "Male",                             // doctorGender
                "1234567890",                       // doctorMobileNumber
                "Azmi Khan",                        // doctorName
                "password123",                      // doctorPassword
                "Cardiology",                       // doctorSpecialization
                "City Hospital",                    // hospitalName
                new Date(),                         // registerDate
                null,                               // resetPasswordToken
                new Date()                          // updateDate
        );

        when(helloDoctorServices.addDoctor(any(DoctorRequestDTO.class))).thenReturn(doctorRequestDTO);
        MockHttpServletRequestBuilder mockRequest =
                MockMvcRequestBuilders.post("/doctor/addDoctor")

                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(doctorRequestDTO));

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.doctorEmail")
                        .value("Azmi@gmail.com"));

    }

}
