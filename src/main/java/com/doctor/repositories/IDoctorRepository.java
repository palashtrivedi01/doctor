package com.doctor.repositories;

import com.doctor.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByDoctorEmail(String doctorEmail);

    Boolean existsByDoctorEmail(String doctorEmail);
    Boolean existsByDoctorMobileNumber(String doctorMobileNumber);

}
