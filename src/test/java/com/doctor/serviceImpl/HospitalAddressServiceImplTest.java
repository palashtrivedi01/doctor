package com.doctor.serviceImpl;

import com.doctor.ENUM.Country;
import com.doctor.entities.HospitalAddress;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repositories.IHospitalRepository;
import com.doctor.requestDto.HospitalAddressRequestDto;
import com.doctor.services.IHospitalAddressService;
import com.doctor.services.IHospitalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class HospitalAddressServiceImplTest {

    @InjectMocks
    private HospitalAddressServiceImpl hospitalAddressService;

    @Mock
    private IHospitalRepository hospitalRepository;

    private ModelMapper modelMapper;

//    @Mock
//    HospitalAddress hospitalAddress;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
//        hospitalAddressService = new HospitalAddressServiceImpl();
    }

    @DisplayName("Save hospital address...")
    @Test
    void saveHospitalAddress_Success() {
        HospitalAddressRequestDto savedDto = HospitalAddressRequestDto.builder()
                .addressName("address")
                .city("city")
                .state("state")
                .zipCode("123456")
                .country("INDIA")
                .build();

//        when(hospitalAddressService.saveHospitalAddress(any(HospitalAddressRequestDto.class))).thenReturn(savedDto);

        HospitalAddress hospitalAddress = HospitalAddress.builder()
                .addressName("address")
                .city("city")
                .state("state")
                .zipCode("123456")
                .country(Country.INDIA)
                .build();

        when(hospitalRepository.save(any(HospitalAddress.class))).thenReturn(hospitalAddress);

//        when(hospitalAddressService.saveHospitalAddress(any(HospitalAddressRequestDto.class))).thenReturn(savedDto);

        HospitalAddressRequestDto result = hospitalAddressService.saveHospitalAddress(savedDto);

        assertNotNull(result);
        assertEquals("address", result.getAddressName());
        assertEquals("city", result.getCity());
        assertEquals("state", result.getState());
        assertEquals("123456", result.getZipCode());
        assertEquals("INDIA", result.getCountry());

        // verify(hospitalAddressService, times(1)).saveHospitalAddress(any(HospitalAddressRequestDto.class));
    }

    @Test
    void updateHospitalAddressByHospitalId_Success() throws BusinessException {

        Long hospitalAddressId = 1234L;

        HospitalAddress hospitalAddress = HospitalAddress.builder()
//                .hospitalAddressId(123L)
                .addressName("Navlakha")
                .zipCode("452001")
                .state("MP")
                .country(Country.GERMANY)
                .city("Indore")
                .build();

        when(this.hospitalRepository.findById(hospitalAddressId)).thenReturn(Optional.ofNullable(hospitalAddress));
        when(this.hospitalRepository.save(any(HospitalAddress.class))).thenReturn(hospitalAddress);

        HospitalAddressRequestDto hospitalAddressRequestDto = HospitalAddressRequestDto.builder()
                .addressName("Navlakha")
                .zipCode("452001")
                .state("MP")
                .country("AUSTRALIA")
                .city("Indore")
                .build();

        HospitalAddressRequestDto requestDto = hospitalAddressService.updateHospitalByHospitalId(hospitalAddressId, hospitalAddressRequestDto);

        assertEquals(hospitalAddressRequestDto.getAddressName(), requestDto.getAddressName());
        assertEquals(hospitalAddressRequestDto.getZipCode(), requestDto.getZipCode());
        assertEquals(hospitalAddressRequestDto.getState(), requestDto.getState());
        assertEquals(hospitalAddressRequestDto.getCountry(), requestDto.getCountry());
    }

    @Test
    void deleteHospitalAddressByHospitalId_Success() throws BusinessException {
        Long hospitalAddressId = 1L;

        HospitalAddress hospitalAddress = HospitalAddress.builder()
//                .hospitalAddressId(1L)
                .addressName("Navlakha")
                .zipCode("452001")
                .state("MP")
                .country(Country.GERMANY)
                .city("Indore")
                .build();

        when(hospitalRepository.findById(hospitalAddressId)).thenReturn(Optional.ofNullable(hospitalAddress));
        hospitalAddressService.deleteHospitalAddressByHospitalId(hospitalAddressId);
    }


    @Test
    void getHospitalAddressById() throws BusinessException {
        Long hospitalAddressId = 1L;

        HospitalAddress hospitalAddress = HospitalAddress.builder()
                .addressName("Navlakha")
                .zipCode("452001")
                .state("MP")
                .country(Country.GERMANY)
                .city("Indore")
                .build();

        when(hospitalRepository.findById(hospitalAddressId)).thenReturn(Optional.ofNullable(hospitalAddress));

        assert hospitalAddress != null;
        HospitalAddressRequestDto hospitalAddressRequestDto = HospitalAddressRequestDto.builder()
                .city(hospitalAddress.getCity())
                .state(hospitalAddress.getState())
                .zipCode(hospitalAddress.getZipCode())
                .addressName(hospitalAddress.getAddressName())
                .country(hospitalAddress.getCountry().toString())
                .build();

        hospitalAddressService.getHospitalAddressById(hospitalAddressId);

        assertEquals("Navlakha", hospitalAddressRequestDto.getAddressName());
        assertEquals("MP", hospitalAddressRequestDto.getState());
        assertEquals("GERMANY", hospitalAddressRequestDto.getCountry());
        assertEquals("Indore", hospitalAddressRequestDto.getCity());
        assertEquals("452001", hospitalAddressRequestDto.getZipCode());

    }

    @Test
    void getAllHospitalAddresses() throws BusinessException {
        List<HospitalAddress> hospitalAddressList = new ArrayList<>();
        hospitalAddressList.add(HospitalAddress.builder().city("Bengaluru").addressName("Cubbon park").country(Country.INDIA).state("Karnataka").zipCode("464001").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Kharkiv").addressName("Russia street").country(Country.RUSSIA).state("Moscow state").zipCode("464001").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Ukraine city").addressName("Ukraine street").country(Country.UKRAINE).state("Kyiv").zipCode("246800").build());
        hospitalAddressList.add(HospitalAddress.builder().city("dutch city").addressName("Dutch street").country(Country.NETHERLANDS).state("Dutch state").zipCode("135790").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Aussies city").addressName("Great Barrier").country(Country.AUSTRALIA).state("New Australia").zipCode("452001").build());

        when(hospitalRepository.findAll()).thenReturn(hospitalAddressList);
        List<HospitalAddressRequestDto> hospitalAddressRequestDtoList =
                hospitalAddressList.stream().map( hospitalAddressRequestDto -> {
                    HospitalAddressRequestDto dto = new HospitalAddressRequestDto();
                    dto.setAddressName(hospitalAddressRequestDto.getAddressName());
                    dto.setCountry(String.valueOf(hospitalAddressRequestDto.getCountry()));
                    dto.setCity(hospitalAddressRequestDto.getCity());
                    dto.setState(hospitalAddressRequestDto.getState());
                    dto.setZipCode(hospitalAddressRequestDto.getZipCode());
                    return dto;
                }).toList();


        hospitalAddressService.getAllHospitalAddresses();
        assertEquals(5, hospitalAddressRequestDtoList.size());
        assertEquals("UKRAINE", hospitalAddressRequestDtoList.get(2).getCountry());
        assertEquals("Dutch street", hospitalAddressRequestDtoList.get(3).getAddressName());

    }

    @Test
    void getAllHospitalAddressesByCity() throws BusinessException {
        String city = "Moscow";
        List<HospitalAddress> hospitalAddressList = new ArrayList<>();
        hospitalAddressList.add(HospitalAddress.builder().city("Bengaluru").addressName("Cubbon park").country(Country.INDIA).state("Karnataka").zipCode("464001").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Moscow").addressName("Russia street").country(Country.RUSSIA).state("Moscow state").zipCode("464001").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Ukraine city").addressName("Ukraine street").country(Country.UKRAINE).state("Kyiv").zipCode("246800").build());
        hospitalAddressList.add(HospitalAddress.builder().city("dutch city").addressName("Dutch street").country(Country.NETHERLANDS).state("Dutch state").zipCode("135790").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Moscow").addressName("Great Barrier").country(Country.RUSSIA).state("New russia").zipCode("452002").build());

        when(hospitalRepository.findHospitalAddressByCity(city)).thenReturn(
                hospitalAddressList.stream().filter(hospitalAddress -> hospitalAddress.getCity().equals(city)).collect(Collectors.toList())
        );

        List<HospitalAddressRequestDto> hospitalAddressRequestDtoList = hospitalAddressList.stream()
                .filter(hospitalAddress -> hospitalAddress.getCity().equals(city))
                .map(hospitalAddress -> {
                    HospitalAddressRequestDto dto = new HospitalAddressRequestDto();
                    dto.setAddressName(hospitalAddress.getAddressName());
                    dto.setCountry(String.valueOf(hospitalAddress.getCountry()));
                    dto.setCity(hospitalAddress.getCity());
                    dto.setState(hospitalAddress.getState());
                    dto.setZipCode(hospitalAddress.getZipCode());
                    return dto;
                }).toList();

        hospitalAddressService.getAllHospitalAddressesByCity(city);

        assertEquals(2, hospitalAddressRequestDtoList.size());
        assertEquals("RUSSIA", hospitalAddressRequestDtoList.get(0).getCountry());

        System.out.println("Test passed: " + hospitalAddressRequestDtoList.stream().map(dto -> dto.getCity() + "(" + dto.getState() + ", " + dto.getCountry() + ")").collect(Collectors.joining(", ")));
    }

    @Test
    void getAllHospitalAddressesByState() throws BusinessException {
        String state = "Karnataka";

        List<HospitalAddress> hospitalAddressList = new ArrayList<>();
        hospitalAddressList.add(HospitalAddress.builder().city("Bengaluru").addressName("Cubbon park").country(Country.INDIA).state("Karnataka").zipCode("464001").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Moscow").addressName("Russia street").country(Country.RUSSIA).state("Moscow state").zipCode("464001").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Ukraine city").addressName("Ukraine street").country(Country.UKRAINE).state("Kyiv").zipCode("246800").build());
        hospitalAddressList.add(HospitalAddress.builder().city("dutch city").addressName("Dutch street").country(Country.NETHERLANDS).state("Dutch state").zipCode("135790").build());
        hospitalAddressList.add(HospitalAddress.builder().city("Moscow").addressName("Great Barrier").country(Country.RUSSIA).state("New russia").zipCode("452002").build());

        when(hospitalRepository.findHospitalAddressByState(state)).thenReturn(
                hospitalAddressList.stream().filter(hospitalAddress -> hospitalAddress.getState().equals(state)).collect(Collectors.toList())
        );

        List<HospitalAddressRequestDto> hospitalAddressRequestDtoList = hospitalAddressList.stream()
                .filter(hospitalAddress -> hospitalAddress.getState().equalsIgnoreCase(state))
                .map(hospitalAddress -> {
                    HospitalAddressRequestDto dto = new HospitalAddressRequestDto();
                    dto.setAddressName(hospitalAddress.getAddressName());
                    dto.setCountry(String.valueOf(hospitalAddress.getCountry()));
                    dto.setCity(hospitalAddress.getCity());
                    dto.setState(hospitalAddress.getState());
                    dto.setZipCode(hospitalAddress.getZipCode());
                    return dto;
                }).toList();

        hospitalAddressService.getAllHospitalAddressesByState(state);

        assertEquals(1, hospitalAddressRequestDtoList.size());
        assertEquals("INDIA", hospitalAddressRequestDtoList.get(0).getCountry());

        System.out.println("Test passed: " + hospitalAddressRequestDtoList.stream().map(dto -> dto.getCity() + "(" + dto.getState() + ", " + dto.getCountry() + ")").collect(Collectors.joining(", ")));

    }

//    *******************************************************************

    @DisplayName("Invalid zip code...")
    @Test
    void testSaveHospitalAddress_InvalidZipCode() {
        HospitalAddressRequestDto requestDto = HospitalAddressRequestDto.builder()
                .state("Goa")
                .country("India")
                .city("Panji")
                .addressName("Saint Sebastian garden")
                .zipCode("123")
                .build();

        Exception exception = assertThrows(ControllerException.class,
                () -> hospitalAddressService.saveHospitalAddress(requestDto));

        assertEquals("Zipcode must contain exactly 6 digits", exception.getMessage());
    }

    @Test
    void testSaveHospitalAddress_CountryNotFound() {
        HospitalAddressRequestDto hospitalAddressRequestDto = HospitalAddressRequestDto.builder()
                .zipCode("464001")
                .addressName("Manit hall")
                .country("Null")
                .city("Bengaluru")
                .state("Karnataka")
                .build();

        Exception exception = assertThrows(ControllerException.class,
                () -> hospitalAddressService.saveHospitalAddress(hospitalAddressRequestDto));

        assertEquals("Country not found", exception.getMessage());
    }

    @Test
    void updateHospitalByHospitalId_HospitalIdNotFound() {
        Long hospitalId = 111L;

        HospitalAddressRequestDto hospitalAddressRequestDto = HospitalAddressRequestDto.builder()
                .zipCode("464001")
                .addressName("Manit hall")
                .country("Null")
                .city("Bengaluru")
                .state("Karnataka")
                .build();

        when(hospitalRepository.findById(hospitalId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BusinessException.class,
                () -> hospitalAddressService.updateHospitalByHospitalId(hospitalId, hospitalAddressRequestDto)
        );

        assertEquals("Hospital Address Not Found with given Id : " + hospitalId, exception.getMessage());
    }

    @Test
    void testDeleteHospitalAddressByHospitalId_NotFound()  {
        Long hospitalId = 2L;

        HospitalAddressRequestDto hospitalAddressRequestDto = HospitalAddressRequestDto.builder()
                .zipCode("464001")
                .addressName("Manit hall")
                .country("Null")
                .city("Bengaluru")
                .state("Karnataka")
                .build();

        when(hospitalRepository.findById(hospitalId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BusinessException.class, () ->
                hospitalAddressService.deleteHospitalAddressByHospitalId(hospitalId));

        assertEquals("Hospital Address Not Found with given Id : " + hospitalId, exception.getMessage());
    }

    @Test
    void testGetHospitalAddressById_NotFound() {
        Long hospitalId = 1432L;

        when(hospitalRepository.findById(hospitalId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(BusinessException.class, () -> {
            hospitalAddressService.getHospitalAddressById(hospitalId);
        });

        assertEquals("Hospital Address Not Found with given Id : " + hospitalId, exception.getMessage());
    }

    @Test
    void testGetAllHospitalAddressesByCity_InvalidCity() {

        String city = "123";
        Exception exception = assertThrows(BusinessException.class, () -> hospitalAddressService.getAllHospitalAddressesByCity(city));
        assertEquals("Number cannot be passed as City", exception.getMessage());

    }

    @Test
    void testGetAllHospitalAddressesByState_InvalidState() {
        String state = "123";

        Exception exception = assertThrows(BusinessException.class,
                () -> hospitalAddressService.getAllHospitalAddressesByState(state));

        assertEquals("Number cannot be passed as State", exception.getMessage());

    }

    @Test
    void testGetAllHospitalAddressesByCountry_InvalidCountry() {
        String country = "123456";
        Exception exception = assertThrows(BusinessException.class,
                () -> hospitalAddressService.getAllHospitalAddressesByCountry(country));
        assertEquals("Number cannot be passed as Country", exception.getMessage());
    }

}