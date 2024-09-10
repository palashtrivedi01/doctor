package com.doctor.controller;

import com.doctor.dto.FileInfo;
import com.doctor.dto.ResponseMessage;
import com.doctor.entities.Appointment;
import com.doctor.service.AppointmentServiceImpl;
import com.doctor.service.AppointmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentServiceInterface appointmentService;

    @PostMapping("/")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment appointment1 = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(appointment1);
    }
    @GetMapping("/{patientEmail}")
    public ResponseEntity<Appointment> getPatientByEmail(@PathVariable ("patientEmail") String email) {
//            ResponseEntity<Appointment> appointment=this.getPatientByEmail(email);
        Appointment appointment1 = this.appointmentService.findByPatientEmail(email);
//        return appointment1;
        return ResponseEntity.ok(appointment1);
    }


        @PostMapping("/upload")
        public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
            String message = "";
            try {
                System.out.println("AZMI");
                Appointment appointment=this.appointmentService.store(file);


                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }





}
