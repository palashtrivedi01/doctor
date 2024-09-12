package com.doctor.controller;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.service.DoctorServices;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor/")
public class DoctorController {
    @Autowired
    private DoctorServices doctorServices;

    @GetMapping
    public String check() {
        return "Controller is working";
    }

    Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @PostMapping("adddoctor")
    public ResponseEntity<Doctor> addDoctor(@Valid  @RequestBody DoctorRequestDto doctorRequestDto) {
            Doctor savedDoctor = doctorServices.addDoctor(doctorRequestDto);
            return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);

    }

    @PutMapping("updateDoctor/{doctorEmail}")
    public ResponseEntity<Doctor> updateEmail(@Valid @PathVariable("doctorEmail") String doctorEmail, @RequestBody DoctorRequestDto doctorRequestDto) throws DoctorNotFoundException {
            Doctor doctorRequest = doctorServices.updateDoctor(doctorEmail, doctorRequestDto);
            return new ResponseEntity<>(doctorRequest, HttpStatus.OK);

    }

    @GetMapping("{doctorId}")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@Valid @PathVariable("doctorId") long doctorId) {
       Optional<Doctor> doctor = doctorServices.getDoctorById(doctorId);
       return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/doctoremail/{doctorEmail}")
    public ResponseEntity<Doctor> getDoctorByEmail(@Valid @PathVariable String doctorEmail) throws DoctorNotFoundException {
        Doctor doctor = doctorServices.getDoctorByEmail(doctorEmail);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/alldoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorServices.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @DeleteMapping("/deletedoctorById/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@Valid @PathVariable Long doctorId) throws DoctorNotFoundException {
        doctorServices.deleteDoctorById(doctorId);
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }

    @GetMapping("getAppointment/{doctorEmail}")
    public ResponseEntity<List<Appointment>> getAppoinmentByDoctorEmail(@Valid @PathVariable String doctorEmail){
        List<Appointment> appointment = doctorServices.getAppoinmentByDoctorEmail(doctorEmail);
        return  new ResponseEntity<>(appointment,HttpStatus.OK);
    }

}









