package com.doctor.repository;

import com.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByDoctorEmail(String doctorEmail);



}
