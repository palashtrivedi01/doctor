package com.doctor.repository;

import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByDoctorEmail(String doctorEmail);

}

