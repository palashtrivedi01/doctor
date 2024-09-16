package com.doctor.services;

import com.doctor.entity.Appointment;
import com.doctor.requestdto.AppointmentRequestDto;

import java.util.List;


public interface AppointmentService {
    AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto);
    List<AppointmentRequestDto> findByPatientEmail(String patientEmail) throws Exception;



}
