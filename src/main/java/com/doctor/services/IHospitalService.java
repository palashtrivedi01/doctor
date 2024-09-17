package com.doctor.services;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.HospitalDetailsRequestDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IHospitalService {

    HospitalDetailsRequestDto addHospital(HospitalDetailsRequestDto hospitalDetailsRequestDto);

    HospitalDetailsRequestDto updateHospital(HospitalDetailsRequestDto hospitalDetailsRequestDto, Long hospitalId) throws BusinessException;

    String deleteHospitalByHospitalId(Long hospitalId) throws BusinessException;

    HospitalDetailsRequestDto getHospitalByHospitalId(Long hospitalId) throws BusinessException;

    List<HospitalDetailsRequestDto> getAllHospitals();

    List<HospitalDetailsRequestDto> getAllHospitalsByHospitalName(String hospitalName) throws BusinessException;
}
