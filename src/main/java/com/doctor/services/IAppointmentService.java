package com.doctor.services;

import com.doctor.entities.Appointment;
import com.doctor.requestDto.AppointmentRequestDto;

import java.util.List;

public interface IAppointmentService {

    Appointment saveAppointment(Appointment appointment);

    Appointment findByEmail(String byPatientEmail);

}
