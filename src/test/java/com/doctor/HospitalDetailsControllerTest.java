package com.doctor;
//
//import com.doctor.exception.BusinessException;
//import com.doctor.requestDto.HospitalAddressRequestDto;
//import com.doctor.restcontrollers.HospitalAddressRestController;
//import com.doctor.services.IHospitalAddressService;

import com.doctor.controller.HospitalAddressController;
import com.doctor.dto.HospitalDto;
import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalDetails;
import com.doctor.service.serviceImpl.HospitalDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class HospitalDetailsControllerTest {

    @InjectMocks
    HospitalAddressController hospitalAddressRestController;

    @Mock
    HospitalDetailsServiceImpl hospitalDetailsService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hospitalAddressRestController).build();

    }
//
//    @Test
//    public void addHospital() {
//        HospitalDto hospitalDto = HospitalDto.builder()
//        when(hospitalDetailsService.addHospital(hospitalDto)).thenReturn(hospitalDto);
//    }
}
//
//    @PostMapping("addhospital")
//    public ResponseEntity<HospitalDto> addDoctor(@RequestBody HospitalDto hospitalDto) {
//        HospitalDetails hospitalDto1 = hospitalDetailService.addHospital(hospitalDto);
//        return new ResponseEntity<>(hospitalDto, HttpStatus.OK);
//    }
//
//
//    Doctor doctor1 = Doctor.builder()
//            .doctorSpecialization("demo").doctorName("payal").doctorGender("female")
//            .resetPasswordToken("abc").build();
//    when(doctorServices.addDoctor(doctorRequestDto)).thenReturn(doctor1);
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    String requestBody = objectMapper.writeValueAsString(doctorRequestDto);
//    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//            .post("/doctor/adddoctor", doctorRequestDto)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(requestBody);
//
//        mockMvc.perform(mockRequest)
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$.doctorName").value("payal"))
//            .andExpect(jsonPath("$.doctorSpecialization").value("demo"))
//            .andExpect(jsonPath("$.doctorGender").value("female"))
//            .andDo(MockMvcResultHandlers.print());
//
//}