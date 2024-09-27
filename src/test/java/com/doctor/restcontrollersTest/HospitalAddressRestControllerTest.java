package com.doctor.restcontrollersTest;

import com.doctor.exception.BusinessException;
import com.doctor.repositories.IHospitalRepository;
import com.doctor.requestDto.HospitalAddressRequestDto;
import com.doctor.restcontrollers.HospitalAddressRestController;
import com.doctor.services.IHospitalAddressService;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.hamcrest.Matchers.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class HospitalAddressRestControllerTest {

    @InjectMocks
    HospitalAddressRestController hospitalAddressRestController;

    @Mock
    IHospitalAddressService iHospitalAddressService;

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
        Mockito.when(iHospitalAddressService.saveHospitalAddress(Mockito.any(HospitalAddressRequestDto.class)))
                .thenReturn(hospitalAddressRequestDto);

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
    void updateHospitalByHospitalId_Success() throws Exception {
        Long hospitalId = 5L;
        HospitalAddressRequestDto hospitalAddressRequestDto = HospitalAddressRequestDto.builder()
                .state("mp")
                .addressName("rpr")
                .city("vds")
                .zipCode("464001")
                .country("India")
                .build();
        Mockito.when(this.iHospitalAddressService.updateHospitalByHospitalId(eq(5L), Mockito.any(HospitalAddressRequestDto.class)))
                .thenReturn(hospitalAddressRequestDto);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/api/v3/hms/hospitalAddress/updateHospitalByHospitalId/{hospitalId}", hospitalId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hospitalAddressRequestDto));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.addressName").value("rpr"))
                .andExpect( jsonPath("$.city").value("vds"))
                .andExpect( jsonPath("$.zipCode").value("464001"))
                .andExpect( jsonPath("$.country").value("India"))
                .andExpect( jsonPath("$.state").value("mp"))
//                .andExpect( jsonPath("$.hospitalId").value(5L))
                .andDo(print());

        verify(this.iHospitalAddressService, times(1)).updateHospitalByHospitalId(hospitalId, hospitalAddressRequestDto);

    }

    @Test
    void getHospitalAddressById_Success() throws Exception {
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
        hospitalAddresses.add(HospitalAddressRequestDto.builder()
                        .country("India")
                        .zipCode("452001")
                        .city("Vidisha")
                        .addressName("RPR")
                        .state("Madhya Pradesh")
                        .build());
        Mockito.when(iHospitalAddressService.getAllHospitalAddresses()).thenReturn(hospitalAddresses);

        log.info("GETTING ALL HOSPITALS");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v3/hms/hospitalAddress/getAllHospitalAddresses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(3)))
//                .andExpect(jsonPath("$[0].name").value("Hospital A"))
//                .andExpect(jsonPath("$[0].addressName").value("Address A"))
//                .andExpect(jsonPath("$[1].name").value("Hospital B"))  // Uncommented
//                .andExpect(jsonPath("$[1].addressName").value("Address B")); // Uncommented

//         .andExpect(jsonPath("$[0].addressName").value("Address A"))
//                .andExpect(jsonPath("$[0].city").value("City A"))
//                .andExpect(jsonPath("$[0].state").value("State A"))
//                .andExpect(jsonPath("$[0].zipCode").value("Zip A"))
//                .andExpect(jsonPath("$[0].country").value("Country A"))
                .andExpect(jsonPath("$[2].addressName").value("RPR"))
                .andExpect(jsonPath("$[2].city").value("Vidisha"))
                .andExpect(jsonPath("$[2].state").value("Madhya Pradesh"))
                .andExpect(jsonPath("$[2].zipCode").value("452001"))
                .andExpect(jsonPath("$[2].country").value("India"));

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

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v3/hms/hospitalAddress/deleteHospitalAddressByHospitalId/{hospitalId}",hospitalId))
                .andExpect(status().isAccepted());

        verify(iHospitalAddressService, times(1)).deleteHospitalAddressByHospitalId(hospitalId);

    }

    @Test
    public void getAllHospitalAddressesByCity_Success() throws Exception {
        String city = "Indore";
        List<HospitalAddressRequestDto> hospitalAddresses = new ArrayList<>();
        hospitalAddresses.add(HospitalAddressRequestDto.builder().addressName("MG Road").city("Indore").country("USA").zipCode("123456").state("Texas").build());

        Mockito.when(this.iHospitalAddressService.getAllHospitalAddressesByCity(city)).thenReturn(hospitalAddresses);

        log.info("GETTING ALL HOSPITALS BY CITY");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v3/hms/hospitalAddress/getAllHospitalAddressesByCity/{city}",city)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].state").value("Texas"))
                .andExpect(jsonPath("$[0].country").value("USA"));

        verify(this.iHospitalAddressService, times(1)).getAllHospitalAddressesByCity(city);
    }

    @Test
    public void getAllHospitalAddressesByState_Success() throws Exception {
        String state = "Nevada";
        List<HospitalAddressRequestDto> hospitalAddresses = new ArrayList<>();

        hospitalAddresses.add(HospitalAddressRequestDto
                .builder().state("Nevada").zipCode("654321").country("United States of America").addressName("Street 1").city("Las Angeles").build());
        hospitalAddresses.add(HospitalAddressRequestDto    .builder().state("London").zipCode("321654").country("United Kingdom").addressName("Street 123").city("Manchester").build());
                hospitalAddresses.add(HospitalAddressRequestDto      .builder().state("State of Scotland").zipCode("987654").country("Scotland").addressName("Street 23").city("City of Scotland").build()
        );

        Mockito.when(this.iHospitalAddressService.getAllHospitalAddressesByState(state)).thenReturn(hospitalAddresses);
        log.info("GETTING ALL HOSPITALS BY STATE");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v3/hms/hospitalAddress/getAllHospitalAddressesByState/{state}", state)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].state").value("Nevada"))
                .andExpect(jsonPath("$[1].state").value("London"))
        ;

        verify(this.iHospitalAddressService, times(1)).getAllHospitalAddressesByState(state);

    }




}