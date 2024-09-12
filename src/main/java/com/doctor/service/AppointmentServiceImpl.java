package com.doctor.service;

import com.doctor.entities.Appointment;
import com.doctor.exception.ControllerException;
import com.doctor.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentServiceInterface {
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public ResponseEntity<Appointment> createAppointment(Appointment appointment) {

        Appointment appointment1 = this.appointmentRepository.save(appointment);

        if(appointment1 != null) {
            return ResponseEntity.ok(appointment1);
        }else
            throw new ControllerException("Data for table is mendatory for creating appointment");
    }


    @Override
    public ResponseEntity<Appointment> findByPatientEmail(String email) {

        Appointment appointment = this.appointmentRepository.findByPatientEmail(email);
        if(email!= null) {
        return ResponseEntity.ok(appointment);
    }
        else
        {
        throw new ControllerException("email is must for finding patient appointment");
        }
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




