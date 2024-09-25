package com.doctor.repository;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

   // public Appointment findByPatientEmail(String email);

    Appointment findByPatientEmail(String email);

    List<Appointment> findByDoctorEmail(String doctorEmail);
}
