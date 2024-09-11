package com.doctor.repository;

import com.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByDoctorEmail(String doctorEmail);
    Optional<Doctor> findByDoctorId(Long doctorId);


}
