package com.doctor.repository;

import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalAddressRepository extends JpaRepository<HospitalAddress,Long> {

}
