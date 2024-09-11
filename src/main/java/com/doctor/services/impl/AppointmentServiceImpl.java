package com.doctor.services.impl;

import com.doctor.entity.Appointment;
import com.doctor.repository.AppointmentRepo;
import com.doctor.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private ResourceUrlProvider mvcResourceUrlProvider;


    @Override
    public Appointment saveAppointment(Appointment appointment) {

        Appointment appointment1 = appointmentRepo.save(appointment);
        return appointment1;
    }

    @Override
    public Appointment FindByEmail(String patientEmail) {
        Optional<Appointment> optionalAppointment = appointmentRepo.findByPatientEmail(patientEmail);
        return optionalAppointment.orElse(null);
    }



}