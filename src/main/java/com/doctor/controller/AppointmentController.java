package com.doctor.controller;
import com.doctor.dto.AppointmentRequestDto;
import com.doctor.dto.AppointmentResponceDto;
import com.doctor.entities.Appointment;
import com.doctor.service.AppointmentService;
//import com.doctor.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
//
//    @Autowired
//    private FileStorageService fileStorageService;
//
//    @PostMapping("/saveAppointment")
//    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
//        Appointment appointment1 = this.appointmentService.saveAppointment(appointment);
//        return ResponseEntity.ok(appointment1);
//    }

    @GetMapping("/getAppointment/{patientEmail}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable String patientEmail) {
        Appointment byEmail = this.appointmentService.findByEmail(patientEmail);
        return ResponseEntity.ok(byEmail);
    }


    @PostMapping("/save")
    public ResponseEntity<AppointmentResponceDto> saveAppoinment(
            @RequestPart("appoinment") AppointmentRequestDto appointmentRequestDto,
            @RequestPart("file") MultipartFile file) throws IOException {
        AppointmentResponceDto savedAppointment = appointmentService.saveAppoinment(appointmentRequestDto, file);
        return  new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }
}
)
public AppointmentResponceDto saveAppoinment(
        @RequestPart("appoinment") AppointmentRequestDto appointmentRequestDto,
        @RequestPart("file") MultipartFile file) throws IOException {
    appointmentRequestDto.setFile(file);
    return appointmentService.saveAppoinment(appointmentRequestDto);

}