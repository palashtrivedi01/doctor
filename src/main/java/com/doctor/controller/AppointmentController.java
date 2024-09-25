package com.doctor.controller;
import com.doctor.dto.AppointmentRequestDto;
import com.doctor.dto.Response;
import com.doctor.entities.Appointment;
import com.doctor.exception.EmptyInputException;
import com.doctor.service.AppointmentService;
import com.doctor.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;


@RestController
@RequestMapping("/appointment")
@Slf4j
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/saveAppointment",consumes = "multipart/form-data")
    public AppointmentRequestDto saveAppointment(@ModelAttribute AppointmentRequestDto appointmentRequestDto,
                                                 @RequestPart("file") MultipartFile file) {
        String fileAttech = fileStorageService.storeFile(file);

        if (ObjectUtils.isEmpty(fileAttech)) {
            throw new EmptyInputException("file is empty");
        }
        appointmentRequestDto.setFileAttech(fileAttech);
        return appointmentService.saveAppointment(appointmentRequestDto);
    }

    @GetMapping("/getAppointment/{patientEmail}")
    public ResponseEntity<Appointment> getAppointment(@Valid @PathVariable String patientEmail) {
        log.info("Dummy data ");
        log.trace("tracce");
        Appointment byEmail = this.appointmentService.findByEmail(patientEmail);
        return ResponseEntity.ok(byEmail);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MalformedURLException {
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }




}



//
//@PostMapping("/saveAppointment")
//public ResponseEntity<AppointmentRequestDto> saveAppointment(@Valid  @RequestBody AppointmentRequestDto appointmentRequestDto) {
//    AppointmentRequestDto appointmentRequestDto1 = this.appointmentService.saveAppointment(appointmentRequestDto);
//    return ResponseEntity.ok(appointmentRequestDto1);
//}
