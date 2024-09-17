package com.doctor.repository;

import com.doctor.entity.HospitalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDetailsRepo extends JpaRepository<HospitalDetails, Long> {
    HospitalDetails findByHospitalName(String hospitalName);
}
