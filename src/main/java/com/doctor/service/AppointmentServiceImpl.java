package com.doctor.service;

import com.doctor.entities.Appointment;
import com.doctor.repository.AppointmentRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AppointmentServiceImpl implements AppointmentServiceInterface {
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public Appointment createAppointment(Appointment appointment) {

        Appointment ap = this.appointmentRepository.save(appointment);

        return ap;
    }


    @Override
    public Appointment findByPatientEmail(String email) {

        Appointment ap = this.appointmentRepository.findByPatientEmail(email);
        return ap;
    }

   /* private final Path root = Paths.get("uploads");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }



    @Override
    public Appointment store(MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
        return null;*/
    }




