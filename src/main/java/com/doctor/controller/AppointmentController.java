package com.doctor.controller;

//import com.doctor.dto.FileInfo;
//import com.doctor.dto.ResponseMessage;
import com.doctor.dto.AppointmentRequestDto;
import com.doctor.entities.Appointment;
import com.doctor.fileuploading.EmptyInputException;
import com.doctor.fileuploading.FileStorageService;
import com.doctor.service.AppointmentServiceInterface;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Optional;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Slf4j
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServiceInterface appointmentService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "/saveAppointment", consumes = "multipart/form-data")
    public AppointmentRequestDto saveAppointment(@ModelAttribute AppointmentRequestDto appointmentRequestDto,
                                                 @RequestPart("file") MultipartFile file) {

        String fileAttech = fileStorageService.storeFile(file);

        if (ObjectUtils.isEmpty(fileAttech)) {
            throw new EmptyInputException("file is empty");
        }
        appointmentRequestDto.setFileAttech(fileAttech);
        return appointmentService.addAppointment(appointmentRequestDto);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MalformedURLException {
        // Load file as Resource
//        Resource resource = (Resource) fileStorageService.loadFileAsResource(fileName);

        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
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
//    @PostMapping("/createAppointment")
//    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
//        Appointment appointment1 = appointmentService.createAppointment(appointment).getBody();
//
//        log.info("Data");
//        return ResponseEntity.ok(appointment1);
//    }

//    @GetMapping("/{patientEmail}")
//    public ResponseEntity<Appointment> getPatientByEmail(@PathVariable("patientEmail") String email) {
//
//        ResponseEntity<Appointment> appointment1 = this.appointmentService.findByPatientEmail(email);
////
////        logger.info("Log level: WARN");
////        logger.debug("Log level: ERROR");
//        return appointment1;
//    }
//}
//

//
//    @GetMapping("/{patientEmail}")
//    public ResponseEntity<AppointmentRequestDto> getPatientByEmail(@PathVariable("patientEmail") String email) throws FileNotFoundException {
//        ResponseEntity<AppointmentRequestDto> fileEntity = appointmentService.findByPatientEmail(email);
////       AppointmentRequestDto requestDto = fileEntity.getBody();
//
//        String file = fileEntity.getBody().getFileAttech();
//
//        InputStream resource=this.fileStorageService.downloadFile( "Downloads/");
//
//        StringUtils.copy(resource,response.getOutputStream());
//
//        return new ResponseEntity<AppointmentRequestDto>(fileEntity.getStatusCode());
//    }
//}

//
//    @GetMapping("/download")
//    public ResponseEntity<Resource> downloadFile(@RequestParam String email) {
//        ResponseEntity<AppointmentRequestDto> userOpt = appointmentService.findByPatientEmail(email);
//
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//
//        AppointmentRequestDto appointmentRequestDto = userOpt.get();
//        File file = new File(appointmentRequestDto.getFilePath());
//
//        if (!file.exists()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//
//        Resource resource = new FileSystemResource(file);
//        String contentType;
//        try {
//            contentType = java.nio.file.Files.probeContentType(file.toPath());
//        } catch (IOException e) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
//                .body(resource);
//    }
//}
//
//
//
//
//
//
//



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




