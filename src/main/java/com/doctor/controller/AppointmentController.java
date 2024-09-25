package com.doctor.controller;

import com.doctor.exception.EmptyInputException;
import com.doctor.exception.FileStorageException;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.services.AppointmentService;
import com.doctor.services.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/saveAppointment", consumes = "multipart/form-data")
    public ResponseEntity<AppointmentRequestDto> saveAppointment(@ModelAttribute AppointmentRequestDto appointmentRequestDto,
                                                                 @RequestPart MultipartFile file)
            throws EmptyInputException, FileStorageException {
        System.out.println(appointmentRequestDto);
        String fileAttach = fileStorageService.storeFile(file);
        if (ObjectUtils.isEmpty(fileAttach)) {
            throw new EmptyInputException("file is empty");
        }
        appointmentRequestDto.setFileAttach(fileAttach);
        AppointmentRequestDto appointment1 = this.appointmentService.saveAppointment(appointmentRequestDto);
        return new ResponseEntity<>(appointment1, HttpStatus.OK);
    }

    @GetMapping("/getAppointment/{patientEmail}")
    public ResponseEntity<List<AppointmentRequestDto>> getAppointmentByPatientEmail(@PathVariable String patientEmail) throws Exception {
        List<AppointmentRequestDto> appointment = this.appointmentService.findByPatientEmail(patientEmail);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @GetMapping("/images/{appointmentId}")
    public ResponseEntity<AppointmentRequestDto> serveUserImage(@PathVariable Long appointmentId, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("111");
        AppointmentRequestDto appointment = appointmentService.getAppointment(appointmentId);
        System.out.println("3");
        String fileName= appointment.getFileAttach();
        InputStream resource= fileStorageService.getResource("image/", fileName);
        StreamUtils.copy(resource, httpServletResponse.getOutputStream());
        System.out.println(appointment);
        System.out.println("222");
        return new ResponseEntity<>(appointment, HttpStatus.OK);

    }
}
