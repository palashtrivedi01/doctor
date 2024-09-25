package com.doctor.service;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.entities.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AppointmentServiceInterface {

    //public ResponseEntity<Appointment> createAppointment(Appointment appointment);


    AppointmentRequestDto addAppointment(AppointmentRequestDto appointmentRequestDto);

    AppointmentRequestDto findByPatientEmail(String email);
}

