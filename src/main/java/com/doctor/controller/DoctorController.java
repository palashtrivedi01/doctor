package com.doctor.controller;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repository.DoctorRepo;
import com.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor doctor1 = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(doctor1, HttpStatus.OK);
    }

    @PutMapping("/updateDoctor/{doctorEmail}")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor,@PathVariable String doctorEmail) throws BusinessException {
            doctorService.updateDoctor(doctor,doctorEmail);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
    }


    @GetMapping("/doctorEmail/{doctorEmail}")
    public ResponseEntity<Doctor> getDoctorByDoctorEmail(@PathVariable String doctorEmail) throws ControllerException {
        Doctor byDoctorEmail = doctorService.getDoctorByDoctorEmail(doctorEmail);
        return new ResponseEntity<>(byDoctorEmail, HttpStatus.OK);

    }

    @GetMapping("doctorId/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long doctorId) throws BusinessException, ControllerException {
        Doctor doctorByDoctorId = doctorService.getDoctorByDoctorId(doctorId);
        return new ResponseEntity<>(doctorByDoctorId, HttpStatus.OK);
    }

    @GetMapping("/allDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() throws ControllerException{
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);
    }

    @DeleteMapping("deleteDoctorById/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId) throws ControllerException {
        String s = doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/getAppointment/{doctorEmail}")
    public ResponseEntity<List<Appointment>> getAppointmentByDoctorEmail(@PathVariable String doctorEmail) throws BusinessException {
        List<Appointment> appointmentByDoctorEmail = doctorService.getAppointmentByDoctorEmail(doctorEmail);
        return new ResponseEntity<>(appointmentByDoctorEmail, HttpStatus.OK);
    }



}
