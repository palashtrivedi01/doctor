package com.doctor.services;

import com.doctor.exception.BusinessException;
import com.doctor.requestdto.HospitalDetailsRequestDto;
import java.util.List;

public interface HospitalDetailsService {

    HospitalDetailsRequestDto addHospitalDetails(HospitalDetailsRequestDto hospitalDetailsRequestDto) throws BusinessException;

    HospitalDetailsRequestDto getHospitalDetailsById(Long hospitalId) throws BusinessException;

    List<HospitalDetailsRequestDto> getAllHospitalDetails() throws BusinessException;

    HospitalDetailsRequestDto getHospitalByName(String hospitalName) throws BusinessException;
}
