package com.doctor.services;

import com.doctor.entity.Appointment;
import com.doctor.repository.AppointmentRepo;
import org.springframework.web.multipart.MultipartFile;


public interface AppointmentService {
    public Appointment saveAppointment(Appointment appointment);

    public Appointment FindByEmail(String patientEmail);

}
