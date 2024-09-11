package com.doctor.repository;

import com.doctor.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    public Optional<Appointment> findByPatientEmail(String appointment_email);
}
