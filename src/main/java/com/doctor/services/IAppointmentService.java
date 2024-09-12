package com.doctor.services;

import com.doctor.entities.Appointment;
import com.doctor.requestDto.AppointmentRequestDto;

import java.util.List;

public interface IAppointmentService {

    AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto);

    AppointmentRequestDto findByEmail(String byPatientEmail);

}
