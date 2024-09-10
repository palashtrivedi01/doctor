package com.doctor.repositories;

import com.doctor.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

    public Appointment findByPatientEmail(String email);

}
