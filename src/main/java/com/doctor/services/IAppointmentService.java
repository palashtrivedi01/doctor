package com.doctor.services;

import com.doctor.entities.Appointment;

public interface IAppointmentService {

    Appointment saveAppointment(Appointment appointment);

    Appointment findByEmail(String byPatientEmail);
}
