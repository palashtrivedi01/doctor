package com.doctor.controller;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.entities.Doctor;
import com.doctor.entities.Patient;
import com.doctor.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/patient/")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("getAllPatients")
    public ResponseEntity<List<Patient>> getAllPatients(@RequestBody Patient patient){
        List<Patient> patients = patientService.getAllPatient();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping("addpatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient savedPatient = patientService.addPatient(patient);
        return  new ResponseEntity<>(savedPatient,HttpStatus.OK);
    }
}
