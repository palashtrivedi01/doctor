package com.doctor.services;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.HospitalAddressRequestDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IHospitalAddressService {

    HospitalAddressRequestDto saveHospitalAddress(HospitalAddressRequestDto hospitalAddressRequestDto);

    HospitalAddressRequestDto getHospitalAddressById(Long HospitalAddressId) throws BusinessException;

    HospitalAddressRequestDto updateHospitalByHospitalId(Long hospitalId, HospitalAddressRequestDto hospitalAddressRequestDto) throws BusinessException;

    String deleteHospitalAddressByHospitalId(Long hospitalId) throws BusinessException;

    List<HospitalAddressRequestDto> getAllHospitalAddresses();
}
