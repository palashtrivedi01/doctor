package com.doctor.service;
import com.doctor.dto.AppointmentRequestDto;
import com.doctor.entities.Appointment;

public interface AppointmentService {

    Appointment findByEmail(String patientEmail);
    AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto);

}
