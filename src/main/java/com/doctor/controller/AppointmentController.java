package com.doctor.controller;

//import com.doctor.dto.FileInfo;
//import com.doctor.dto.ResponseMessage;
import com.doctor.entities.Appointment;
import com.doctor.service.AppointmentServiceImpl;
import com.doctor.service.AppointmentServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    Logger logger
            = LoggerFactory.getLogger(AppointmentController.class);


    @Autowired
    private AppointmentServiceInterface appointmentService;



//
//    @PostMapping
//    public ResponseEntity<String> createAppointment(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("patientId") Long patientId,
//            @RequestParam("appointmentDate") String appointmentDate) {
//
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("File is empty");
//        }
//
//        try {
//            // Get the file name
//            String fileName = file.getOriginalFilename();
//
//            // Get the file content as byte array
//            byte[] fileContent = file.getBytes();
//
//            // Get the file size
//            long fileSize = file.getSize();
//
//            // Get the file type
//            String fileType = file.getContentType();
//
//            // TODO: Process the file (e.g., save to disk or database)
//            // For example:
//            // fileStorageService.saveFile(fileName, fileContent);
//
//             //TODO: Create and save the appointment
//            // For example:
//             Appointment appointment = new Appointment();
//             appointment.setPatientId(patientId);
//             appointment.setAppointmentDate(LocalDate.parse(appointmentDate));
//             appointment.setFileName(fileName);
//             appointmentRepository.save(appointment);
//
//            return ResponseEntity.ok("Appointment created successfully with file: " + fileName);
//        } catch (IOException e) {
//            return ResponseEntity.internalServerError().body("Failed to process file: " + e.getMessage());
//        }
//    }




    @PostMapping("/createAppointment")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        Appointment appointment1 = appointmentService.createAppointment(appointment).getBody();
        logger.info("Log level: INFO");
        logger.debug("Log level: DEBUG");
        return ResponseEntity.ok(appointment1);
    }

    @GetMapping("/{patientEmail}")
    public ResponseEntity<Appointment> getPatientByEmail(@PathVariable("patientEmail") String email) {

        ResponseEntity<Appointment> appointment1 = this.appointmentService.findByPatientEmail(email);

        logger.info("Log level: WARN");
        logger.debug("Log level: ERROR");
        return appointment1;
    }
}

      /*  @PostMapping("/upload")
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

*/




