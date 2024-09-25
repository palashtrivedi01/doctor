package com.doctor.service;

import com.doctor.dto.HospitalDetailsDto;
import com.doctor.entities.HospitalDetails;

import java.util.List;
import java.util.Optional;

public interface HospitalDetailInterface {


   HospitalDetailsDto addNewHospital(HospitalDetailsDto hospitalDetailsDto);

   HospitalDetailsDto getHospitalDetailsByHospitalId(Long hospitalId);

   List<HospitalDetails> getAllHospitals();


   List<HospitalDetails> getAllHospitalsByName(String hospitalName);
}
