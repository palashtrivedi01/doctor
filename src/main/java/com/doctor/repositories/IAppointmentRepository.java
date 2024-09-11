package com.doctor.repositories;

import com.doctor.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByPatientEmail(String email);

    List<Appointment> findByDoctorEmail(String doctorEmail);
}
