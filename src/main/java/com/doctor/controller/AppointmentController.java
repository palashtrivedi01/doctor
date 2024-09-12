package com.doctor.controller;
import com.doctor.dto.AppointmentRequestDto;
import com.doctor.dto.AppointmentResponceDto;
import com.doctor.entities.Appointment;
import com.doctor.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/saveAppointment")
    public ResponseEntity<Appointment> saveAppointment(@Valid  @RequestBody Appointment appointment) {
        Appointment appointment1 = this.appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(appointment1);
    }

    @GetMapping("/getAppointment/{patientEmail}")
    public ResponseEntity<Appointment> getAppointment(@Valid @PathVariable String patientEmail) {
        Appointment byEmail = this.appointmentService.findByEmail(patientEmail);
        return ResponseEntity.ok(byEmail);
    }


}

//
//@PostMapping("/save")
//public ResponseEntity<String> saveAppoinment(
//        @RequestPart("appoinment") AppointmentRequestDto appointmentRequestDto,
//        @RequestPart("file") MultipartFile file) throws IOException {
//    AppointmentResponceDto savedAppointment = appointmentService.saveAppoinment(appointmentRequestDto, file);
//        return  new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
//
//}
//}
//
//
//@Override
//public AppointmentResponceDto saveAppoinment(AppointmentRequestDto appointmentRequestDto, MultipartFile file) throws IOException {
//    String fileName = null;
//    if(file!= null && !file.isEmpty()){
//        fileName=saveFile(file);
//        appointmentRequestDto.setFile(file);
//    }
//    Appointment appointment = modelMapper.map(appointmentRequestDto,Appointment.class);
//
//    Appointment saveedAppoinment = appointmentRepository.save(appointment);
//
//    AppointmentResponceDto responceDto= modelMapper.map(saveedAppoinment,AppointmentResponceDto.class);
//
//    return responceDto;
//}
//private String saveFile (MultipartFile file) throws IOException{
//    String originalFileName = file.getOriginalFilename();
//    String uniqueFileName = System.currentTimeMillis() + " " + originalFileName;
//    Path filePath = Paths.get(uploadDirectory,uniqueFileName);
//    Files.createDirectories((filePath.getParent()));
//    Files.write(filePath,file.getBytes());
//    return  uniqueFileName;
//}
//}



