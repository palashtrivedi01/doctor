package com.doctor.controller;

import com.doctor.entities.Doctor;
import com.doctor.repository.DoctorRepository;
import com.doctor.service.HelloDoctorServices;
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
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/home")
    public String home() {
        return "DoctorController is working correctly";
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(savedDoctor);
    }
@PutMapping("/updateDoctor/{doctorEmail}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorEmail") String doctorEmail,@RequestBody Doctor doctor) {
      Doctor updatedDoctor=this.doctorService.updateDoctor(doctorEmail, doctor);
        return ResponseEntity.ok(updatedDoctor);
}

//getDoctorById

    @GetMapping("/{doctorId}")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@PathVariable("doctorId") Long doctorId) {
        Optional<Doctor> doctor=this.doctorService.findDoctorById(doctorId);
        return ResponseEntity.ok(doctor);
    }

    //getdoctorByEmail

    @GetMapping("/doctoremail/{doctorEmail}")

    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable("doctorEmail") String doctorEmail)  {

        Doctor doctor=doctorService.findByDoctorEmail(doctorEmail);
        return ResponseEntity.ok(doctor);



    }

    /*public Doctor getByEmail(@PathVariable("doctorEmail") String doctorEmail) {
        Doctor doctor=this.doctorService.findByEmail(doctorEmail);
       // return ResponseEntity.ok(doctor);
        return doctor;
    }*/

    @GetMapping("doctor/allDoctor")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctor=this.doctorService.findAllDoctor();
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable long doctorId) {

        Doctor doctor;
        this.doctorService.deleteByDoctorId(doctorId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

}
