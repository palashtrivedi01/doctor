package com.doctor.restcontrollersTest;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.HospitalAddressRequestDto;
import com.doctor.restcontrollers.HospitalAddressRestController;
import com.doctor.services.IHospitalAddressService;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.apache.commons.lang3.function.Failable.get;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
public class HospitalAddressRestControllerTest {

    @InjectMocks
    HospitalAddressRestController hospitalAddressRestController;

    @Mock
    IHospitalAddressService iHospitalAddressService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hospitalAddressRestController).build();
    }

    @Test
    void saveHospitalAddress_Success() throws Exception {
        HospitalAddressRequestDto hospitalAddressRequestDto = new HospitalAddressRequestDto("country","address", "city", "state", "zip");
        Mockito.when(iHospitalAddressService.saveHospitalAddress(Mockito.any(HospitalAddressRequestDto.class))).thenReturn(hospitalAddressRequestDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v3/hms/hospitalAddress/saveHospital")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hospitalAddressRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect( jsonPath("$.state").value("address"));
//                .andExpect( jsonPath("$.description", is("Gadgets like tv, phone, ac, etc.")))
//                .andExpect( jsonPath("$.id", is("1")));


    }

    @Test
    void getHospitalAddressById_Succes() throws Exception {
        Long hospitalId = 1L;

        HospitalAddressRequestDto hospitalAddressRequestDto = new HospitalAddressRequestDto();

        hospitalAddressRequestDto = new HospitalAddressRequestDto();
        hospitalAddressRequestDto.setAddressName("a");
        hospitalAddressRequestDto.setCity("ci");
        hospitalAddressRequestDto.setState("s");
        hospitalAddressRequestDto.setZipCode("z");

        Mockito.when(iHospitalAddressService.getHospitalAddressById(hospitalId)).thenReturn(hospitalAddressRequestDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/v3/hms/hospitalAddress/getHospitalAddressById/{hospitalId}",hospitalId)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressName").value("a"))
                .andExpect(jsonPath("$.city").value("ci"))
                .andExpect(jsonPath("$.state").value("s"))
                .andExpect(jsonPath("$.zipCode").value("z"));
        verify(iHospitalAddressService, times(1)).getHospitalAddressById(hospitalId);
    }

    @Test
    void getAllHospitalAddresses_Success() throws Exception {
        List<HospitalAddressRequestDto> hospitalAddresses = new ArrayList<>();
        hospitalAddresses.add(new HospitalAddressRequestDto("Hospital A", "Address A", "City A", "State A", "Zip A"));
        hospitalAddresses.add(new HospitalAddressRequestDto("Hospital B", "Address B", "City B", "State B", "Zip B"));

        Mockito.when(iHospitalAddressService.getAllHospitalAddresses()).thenReturn(hospitalAddresses);

        log.info("GETTING ALL HOSPITALS");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/hms/hospitalAddress/getAllHospitalAddresses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Hospital A"))
                .andExpect(jsonPath("$[0].addressName").value("Address A"))
                .andExpect(jsonPath("$[1].name").value("Hospital B"))  // Uncommented
                .andExpect(jsonPath("$[1].addressName").value("Address B")); // Uncommented

        verify(iHospitalAddressService, times(1)).getAllHospitalAddresses();
    }

    @Test
    void deleteHospitalByHospitalId_Success() throws Exception {
        Long hospitalId = 1L;
        HospitalAddressRequestDto hospitalAddressRequestDto = new HospitalAddressRequestDto();
        hospitalAddressRequestDto.setCountry("country");
        hospitalAddressRequestDto.setAddressName("address");
        hospitalAddressRequestDto.setCity("city");
        hospitalAddressRequestDto.setState("state");
        hospitalAddressRequestDto.setZipCode("zipcode");

        Mockito.when(iHospitalAddressService.deleteHospitalAddressByHospitalId(hospitalId)).thenReturn("Deleted successfully");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v3/hms/hospitalAddress/deleteHospital/{hospitalId}",hospitalId))
                .andExpect(status().isAccepted());

        verify(iHospitalAddressService, times(1)).deleteHospitalAddressByHospitalId(hospitalId);

    }



}