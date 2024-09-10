package com.doctor.restcontrollers;

import com.doctor.entities.Appointment;
import com.doctor.repositories.IAppointmentRepository;
import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.responseDto.AppointmentResponseDto;
import com.doctor.services.IAppointmentService;
import com.doctor.services.IFileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v3/hms/appointment")
public class AppointmentRestController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @Autowired
    private IFileService iFileService;

    /*
    *In the Spring Framework,
    * a MultiValueMap is a specialized map that holds multiple values against a single key.
    * It's beneficial for handling HTTP request parameters, headers, and
    * in other scenarios where one key might correspond to multiple values.
     */
    @PostMapping("/saveAppointment/{appointmentId}")
    public ResponseEntity<Appointment> saveAppointment
    (@PathVariable Long appointmentId, @RequestBody Appointment appointment, @RequestParam("file") MultipartFile file) throws IOException {

//        byte[] file1 = file.getBytes();
//        iFileService.saveFile(file1);
          iFileService.saveFile(file);

        return new ResponseEntity<Appointment>
                (this.iAppointmentService.saveAppointment(appointment), HttpStatus.CREATED);
    }

//    @PostMapping("/saveAppointment")
//    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
//        return new ResponseEntity<>(this.iAppointmentService.saveAppointment(appointment), HttpStatus.CREATED);
//    }

    @GetMapping("/getAppointment/{byPatientEmail}")
    public ResponseEntity<Appointment> getAppointmentByPatientEmail(@PathVariable String byPatientEmail) {
        return new ResponseEntity<>(this.iAppointmentService.findByEmail(byPatientEmail), HttpStatus.OK);
    }
}
