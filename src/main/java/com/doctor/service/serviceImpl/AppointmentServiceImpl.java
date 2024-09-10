package com.doctor.service.serviceImpl;

import com.doctor.entities.Appointment;
import com.doctor.repository.AppointmentRepository;
import com.doctor.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;


   


    @Override
    public Appointment findByEmail(String patientEmail) {
        Optional<Appointment> byPatientEmail = appointmentRepository.findByPatientEmail(patientEmail);
        return byPatientEmail.orElse(null);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        Appointment save = this.appointmentRepository.save(appointment);
        return save;
    }
}
