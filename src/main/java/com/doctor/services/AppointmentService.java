package com.doctor.services;

import com.doctor.entity.Appointment;


public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);
    Appointment findByPatientEmail(String patientEmail) throws Exception;
    Appointment findAppointmentByDoctorEmail(String doctorEmail) throws Exception;


}
