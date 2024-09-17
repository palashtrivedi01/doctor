package com.doctor.repositories;

import com.doctor.entities.HospitalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHospitalDetailsRepository extends JpaRepository<HospitalDetails, Long> {

    List<HospitalDetails> findByHospitalName(String hospitalName);

}
