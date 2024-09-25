package com.doctor.repository;

import com.doctor.dto.HospitalDto;
import com.doctor.entities.HospitalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalDetailsRepository extends JpaRepository<HospitalDetails,Long> {
//    List<HospitalDto> findByNameContainingIgnoreCase(String hospitalName);
}
