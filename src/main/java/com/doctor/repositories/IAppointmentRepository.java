package com.doctor.repositories;

import com.doctor.entities.Appointment;
import com.doctor.requestDto.AppointmentRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientEmail(String email);

    List<Appointment> findByDoctorEmail(String doctorEmail);
}
