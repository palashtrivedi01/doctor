package com.doctor.services;

import com.doctor.entity.Appointment;
import com.doctor.requestdto.AppointmentRequestDto;

import java.util.List;


public interface AppointmentService {
    AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto);
    AppointmentRequestDto findByPatientEmail(String patientEmail) throws Exception;



}
