package com.doctor.controller;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.repository.DoctorRepo;
import com.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    private DoctorRepo doctorRepo;


    @GetMapping("/")
    public String welcome(@RequestBody String msg) {
        return msg;

    }

    @PostMapping("/addDoctor")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        Doctor doctor1 = doctorService.addDoctor(doctor);
        return doctor1;
    }

    @PutMapping("/updateDoctor/{doctorEmail}")
    public Doctor updateDoctor(@RequestBody Doctor doctor,@PathVariable String doctorEmail) throws DoctorNotFoundException{
            doctorService.updateDoctor(doctor,doctorEmail);
            return doctor;
    }


    @GetMapping("/doctorEmail/{doctorEmail}")
    public ResponseEntity<Doctor> getDoctorByDoctorEmail(@PathVariable String doctorEmail) throws DoctorNotFoundException {
        Doctor byDoctorEmail = doctorService.getDoctorByDoctorEmail(doctorEmail);
        return ResponseEntity.ok(byDoctorEmail);

    }

    @GetMapping("doctorId/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) throws DoctorNotFoundException {
        Doctor doctorByDoctorId = doctorService.getDoctorByDoctorId(doctorId);
        return ResponseEntity.ok(doctorByDoctorId);
    }

    @GetMapping("/allDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(allDoctors);
    }

    @DeleteMapping("deleteDoctorById/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId) throws DoctorNotFoundException {
        String s = doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok(s);
    }

    @GetMapping("/getAppointment/{doctorEmail}")
    public ResponseEntity<List<Appointment>> getAppointmentByDoctorEmail(@PathVariable String doctorEmail) throws DoctorNotFoundException {
        List<Appointment> appointmentByDoctorEmail = doctorService.getAppointmentByDoctorEmail(doctorEmail);
        return ResponseEntity.ok(appointmentByDoctorEmail);
    }



}
