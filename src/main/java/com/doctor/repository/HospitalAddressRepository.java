package com.doctor.repository;

import com.doctor.dto.HospitalDetailsDto;
import com.doctor.entities.HospitalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalAddressRepository extends JpaRepository<HospitalAddress, Long> {

   // HospitalDetailsDto addNewHospital(HospitalDetailsDto hospitalDetailsDto);
}
