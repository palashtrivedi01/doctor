package com.doctor.service;

import com.doctor.entities.Appointment;
import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface AppointmentServiceInterface {

    public Appointment createAppointment(Appointment appointment);

    public Appointment findByPatientEmail(String email);

    Appointment store(MultipartFile file);
    public void init();

}
