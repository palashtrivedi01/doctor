package com.doctor.repositories;

import com.doctor.entities.HospitalAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHospitalRepository extends JpaRepository<HospitalAddress, Long> {

}
