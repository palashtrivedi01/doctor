package com.doctor.controller;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.entities.Doctor;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.service.DoctorServices;
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

    @PostMapping("adddoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {
        try {
            Doctor savedDoctor = doctorServices.addDoctor(doctorRequestDto);
            return new ResponseEntity<>(savedDoctor, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("unable to add");
        }
    }

    @PutMapping("updateDoctor/{doctorEmail}")
    public ResponseEntity<Doctor> updateEmail(@PathVariable("doctorEmail") String doctorEmail, @RequestBody DoctorRequestDto doctorRequestDto)  {
            Doctor doctorRequest = doctorServices.updateDoctor(doctorEmail, doctorRequestDto);
            return new ResponseEntity<>(doctorRequest, HttpStatus.OK);

    }

    @GetMapping("{doctorId}")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@PathVariable("doctorId") long doctorId) {
       Optional<Doctor> doctor = doctorServices.getDoctorById(doctorId);
       return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/doctoremail/{doctorEmail}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String doctorEmail) throws DoctorNotFoundException {
        Doctor doctor = doctorServices.getDoctorByEmail(doctorEmail);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/alldoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorServices.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @DeleteMapping("/deletedoctorById/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId) throws DoctorNotFoundException {
        doctorServices.deleteDoctorById(doctorId);
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }

}









