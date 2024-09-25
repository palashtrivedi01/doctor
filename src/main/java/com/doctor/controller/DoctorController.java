package com.doctor.controller;

import com.doctor.dto.DoctorRequestDTO;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.service.serviceimpl.HelloDoctorServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private HelloDoctorServices doctorService;
//


    // creating a logger
    Logger logger
            = LoggerFactory.getLogger(DoctorController.class);

    //getting home page
    @GetMapping("/home")
    public String home() {
        return "DoctorController is working correctly";
    }




    //Add Doctor
    @PostMapping("/addDoctor")
    public ResponseEntity<DoctorRequestDTO> addDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) {
        DoctorRequestDTO savedDoctor = doctorService.addDoctor(doctorRequestDTO);
        logger.info("Log level: INFO");
        logger.debug("Log level: DEBUG");
        return ResponseEntity.ok(savedDoctor);
    }


    //update doctor
    @PutMapping("/updateDoctor/{doctorEmail}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorEmail") String doctorEmail, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = this.doctorService.updateDoctor(doctorEmail, doctor);
        logger.error("Log level: ERROR");
        logger.warn("Log level: WARN");
        return ResponseEntity.ok(updatedDoctor);
    }

    //getDoctorById

    @GetMapping("/{doctorId}")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@PathVariable("doctorId") Long doctorId) {
        Optional<Doctor> doctor = this.doctorService.findDoctorById(doctorId);
        logger.info("Log level: INFO");
        logger.debug("Log level: DEBUG");
        return ResponseEntity.ok(doctor);
    }

    //getdoctorByEmail

    @GetMapping("/doctoremail/{doctorEmail}")

    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable("doctorEmail") String doctorEmail) {

        Doctor doctor = doctorService.findByDoctorEmail(doctorEmail);
        return ResponseEntity.ok(doctor);


    }


    //get all doctor
    @GetMapping("doctor/allDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() {

        List<Doctor> doctor = this.doctorService.findAllDoctor();
        return ResponseEntity.ok(doctor);
    }

    //delete doctor
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable long doctorId) {

        Doctor doctor;
        this.doctorService.deleteByDoctorId(doctorId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

    @GetMapping("/appointments/doctor/{doctorEmail}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorEmail(@PathVariable("doctorEmail") String doctorEmail) {
        List<Appointment> appointments = this.doctorService.getAppointmentsByDoctorEmail(doctorEmail);
        return ResponseEntity.ok(appointments);
    }


}
