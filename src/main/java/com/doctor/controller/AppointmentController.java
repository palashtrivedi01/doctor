package com.doctor.controller;
import com.doctor.entities.Appointment;
import com.doctor.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/saveAppointment")
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment){
        Appointment appointment1 = this.appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(appointment1);
    }

    @GetMapping("/getAppointment/{patientEmail}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable String patientEmail){
        Appointment byEmail = this.appointmentService.findByEmail(patientEmail);
        return ResponseEntity.ok(byEmail);
    }



//    @Autowired
//    private FileStorageService fileStorageService;
//
//    @PostMapping("/save")
//    public ResponseEntity<AppointmentResponceDto>saveAppoinment(
//            @RequestPart("appoinment") AppointmentRequestDto appointmentRequestDto,
//            @RequestPart("file") MultipartFile file
//            )
////    {
////        if(file.isEmpty()){
////            throw new EmptyInputException("File not found");
////        }
////    }
//
//    String storedName = fileStorageService.storeFile(file);
//    AppointmentRequestDto.setFileName(storedName);
//
//    AppointmentResponceDto saveAppoinment= appoinmentService.saveAppoinment(AppointmentRequestDto);
//
//    return new ResponseEntity<> (saveAppoinment, HttpStatus.CREATED);


}
