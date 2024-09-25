package com.doctor.service;

import com.doctor.dto.HospitalDto;
import com.doctor.entities.HospitalDetails;
import jakarta.validation.Valid;

import java.util.List;

public interface HospitalDetailService {

    HospitalDetails addHospital(HospitalDto hospitalDto);

    HospitalDto getHopitalById(Long hospitalId);

    List<HospitalDetails> getAllHospital();

//    List<HospitalDto> getHospitalByName(String hospitalName);
}
