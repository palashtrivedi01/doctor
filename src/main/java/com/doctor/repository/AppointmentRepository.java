package com.doctor.repository;

import com.doctor.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
 public Optional<Appointment> findByPatientEmail(String patientEmail);
}
