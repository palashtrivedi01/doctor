package com.doctor.restcontrollers;

import com.doctor.entities.Appointment;
//import com.doctor.payloads.FileResponseMessage;
import com.doctor.exception.BusinessException;
import com.doctor.payloads.ImageResponse;
import com.doctor.repositories.IAppointmentRepository;
import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.responseDto.AppointmentResponseDto;
import com.doctor.services.IAppointmentService;
import com.doctor.services.IFileService;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v3/hms/appointment")
public class AppointmentRestController {

    private final Path path = Paths.get("uploads");

    @Autowired
    private IAppointmentService iAppointmentService;

    @Autowired
    private IFileService iFileService;

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Value("${appointment.profile.image.path}")
    private String imageUploadPath;

    /*
    *In the Spring Framework,
    * a MultiValueMap is a specialized map that holds multiple values against a single key.
    * It's beneficial for handling HTTP request parameters, headers, and
    * in other scenarios where one key might correspond to multiple values.
     */

    /*@PostMapping("/saveAppointment")
    public ResponseEntity<Appointment> saveAppointment
    (@RequestBody Appointment appointment, @RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("TESTING");
          String message = "";
//          iFileService.saveFile(file);
          iFileService.uploadFile(file, String.valueOf(path));
          appointment.setFile(file.getOriginalFilename());
          message="file uploaded successfully with file name : " + file.getOriginalFilename();

        return new ResponseEntity<Appointment>
                (this.iAppointmentService.saveAppointment(appointment), HttpStatus.CREATED);
    }*/

    @PostMapping("/saveAppointment")  //This is working properly
    public ResponseEntity<AppointmentRequestDto> saveAppointment(@Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
        return new ResponseEntity<>(this.iAppointmentService.saveAppointment(appointmentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAppointment/{byPatientEmail}")
    public ResponseEntity<AppointmentRequestDto> getAppointmentByPatientEmail(@PathVariable String byPatientEmail) {
        return new ResponseEntity<>(this.iAppointmentService.findByEmail(byPatientEmail), HttpStatus.OK);
    }


    /*@PostMapping("/saveAppointment")
    public ResponseEntity<FileResponseMessage> saveAppointment
            (@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";
        try {
            iFileService.saveFile(file);

            System.out.println("ORIGINAL NAME : " + file.getOriginalFilename());
            System.out.println("CONTENT TYPE : " + file.getContentType());
            System.out.println("FILE SIZE : " + file.getSize());
            System.out.println("BYTES : " + Arrays.toString(file.getBytes()));
            System.out.println("INPUT STREAM : " + file.getInputStream());
            System.out.println("RESOURCE : " + file.getResource());

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new FileResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileResponseMessage(message));
        }
    }*/

    /*@PostMapping("/saveAppointment/1")
    public ResponseEntity<ImageResponse> uploadFile( @RequestPart("file") MultipartFile images, @RequestBody Appointment appointment) throws Exception
    {
        List<String> imageNames = new ArrayList<>();


            String imageName=iFileService.uploadFile(images, imageUploadPath);
            imageNames.add(imageName);

        appointment.setFile(imageName);
        ImageResponse imageResponse = ImageResponse.builder()
                .imageName(imageNames)
                .message("image uploaded successfully")
                .success(true)
                .status(HttpStatus.CREATED).build();

        appointmentRepository.save(appointment);
        return new ResponseEntity<ImageResponse>(imageResponse,HttpStatus.CREATED);
    }*/

    @PutMapping("/updateAppointment/{appointmentId}")
    public ResponseEntity<AppointmentRequestDto> updateAppointment(@PathVariable Long appointmentId, @Valid @RequestBody AppointmentRequestDto appointmentRequestDto) throws BusinessException {
        return new ResponseEntity<>(this.iAppointmentService.updateAppointment(appointmentId, appointmentRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAppointment/{idOfAppointment}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("idOfAppointment") Long appointmentId) throws BusinessException {
        return new ResponseEntity<>(this.iAppointmentService.deleteAppointment(appointmentId), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAppointmentByAppointmentId")
    public ResponseEntity<AppointmentRequestDto> getAppointmentByAppointmentId(@RequestParam("appointmentId") Long appointmentId) throws BusinessException {
        return new ResponseEntity<>(this.iAppointmentService.getAppointment(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getAllAppointments")
    public ResponseEntity<List<AppointmentRequestDto>> getAllAppointments() throws BusinessException {
        return new ResponseEntity<>(this.iAppointmentService.getAllAppointments(), HttpStatus.OK);
    }






}
