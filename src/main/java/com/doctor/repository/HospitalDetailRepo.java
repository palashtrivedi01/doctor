package com.doctor.repository;

import com.doctor.entities.HospitalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalDetailRepo extends JpaRepository<HospitalDetails, Long> {

    List<HospitalDetails> findHospitalsByHospitalName(String hospitalName);
}
