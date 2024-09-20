package com.doctor.serviceImpl;

import com.doctor.ENUM.Country;
import com.doctor.entities.HospitalAddress;
import com.doctor.repositories.IHospitalRepository;
import com.doctor.requestDto.HospitalAddressRequestDto;
import com.doctor.services.IHospitalAddressService;
import com.doctor.services.IHospitalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HospitalAddressServiceImplTest {

    @Mock
    private IHospitalRepository hospitalRepository;

    @Autowired
    private MockMvc mockMvc;

    private void setup(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hospitalRepository).build();
        hospitalAddressService = new HospitalAddressServiceImpl();
    }

    @InjectMocks
    private HospitalAddressServiceImpl hospitalAddressService;

    @Test
    void saveHospitalAddress() {
        // Arrange
        HospitalAddressRequestDto requestDto = new HospitalAddressRequestDto();
        requestDto.setAddressName("address");
        requestDto.setCity("city");
        requestDto.setState("state");
        requestDto.setCountry("country");

        HospitalAddress savedAddress = new HospitalAddress();
        savedAddress.setAddressName("address");
        savedAddress.setCity("city");
        savedAddress.setState("state");
        savedAddress.setCountry(Country.valueOf("INDIA"));

        when(hospitalRepository.save(any(HospitalAddress.class))).thenReturn(savedAddress);

        // Act
        HospitalAddressRequestDto result = hospitalAddressService.saveHospitalAddress(requestDto);

        // Assert
        assertNotNull(result);
        assertEquals("address", result.getAddressName());
        assertEquals("city", result.getCity());
        assertEquals("state", result.getState());
        assertEquals("country", result.getCountry());

        verify(hospitalRepository, times(1)).save(any(HospitalAddress.class));
    }

}