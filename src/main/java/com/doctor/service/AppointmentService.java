package com.doctor.service;
import com.doctor.entities.Appointment;

public interface AppointmentService {

    public Appointment findByEmail(String patientEmail);
    public Appointment saveAppointment(Appointment appointment);

}
