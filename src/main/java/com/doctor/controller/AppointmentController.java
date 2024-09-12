package com.doctor.controller;

import com.doctor.entity.Appointment;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService  appointmentService;



    @PostMapping("/saveAppointment")
    public ResponseEntity<AppointmentRequestDto> saveAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {

        AppointmentRequestDto appointment1 = this.appointmentService.saveAppointment(appointmentRequestDto);
        return ResponseEntity.ok(appointment1);
    }

    @GetMapping("/getAppointment/{patientEmail}")
    public ResponseEntity<AppointmentRequestDto> getAppointmentByPatientEmail(@PathVariable String patientEmail) throws Exception {
        AppointmentRequestDto appointment = this.appointmentService.findByPatientEmail(patientEmail);

        return ResponseEntity.ok(appointment);

    }




//    @PostMapping("/upload")
//    public ResponseEntity<String>uploadFile(@RequestParam("file") MultipartFile file) {
//
//        // processing and uploading file
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file selected fot Upload");
//        }
//        String uploadDir= "/static/img";
//        File directory = new File(uploadDir);
//        if (!directory.exists()) {
//            directory.mkdirs();
//
//        }
//        try{
//            File destinationFile = new File(uploadDir+"/"+file.getOriginalFilename());
//            file.transferTo(destinationFile);
//            return ResponseEntity.ok(destinationFile.getAbsolutePath());
//        }
//        catch (IOException e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }


}
