package com.doctor.services;

import com.doctor.entity.HospitalAddress;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.requestdto.HospitalAddressRequestDto;

public interface HospitalAddressService {

    HospitalAddressRequestDto getHospitalAddressById(Long id) throws BusinessException, ControllerException;
}
