package com.doctor.restcontrollers;

//import com.doctor.payloads.FileResponseMessage;
import com.doctor.exception.BusinessException;
        import com.doctor.exception.FileStorageException;
        import com.doctor.repositories.IAppointmentRepository;
import com.doctor.requestDto.AppointmentRequestDto;
        import com.doctor.services.IAppointmentService;
import com.doctor.services.IFileService;
//import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
        import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v3/hms/appointment")
public class AppointmentRestController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @Autowired
    private IFileService iFileService;

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Autowired
    private HttpServletResponse httpServletResponse;

    //To upload file without file -
//    @PostMapping("/saveAppointment")
//    public ResponseEntity<AppointmentRequestDto> saveAppointment(@Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
//        return new ResponseEntity<>(this.iAppointmentService.saveAppointment(appointmentRequestDto), HttpStatus.CREATED);
//    }

    @GetMapping("/getAppointment/{byPatientEmail}")
    public ResponseEntity<List<AppointmentRequestDto>> getAppointmentByPatientEmail(@PathVariable String byPatientEmail) {
        return new ResponseEntity<>(this.iAppointmentService.findByEmail(byPatientEmail), HttpStatus.OK);
    }


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

    //////////////////////////////////////////////////////////////////////////////////

    //To upload file with request body -
    @PostMapping(value = "/saveAppointment", consumes = "multipart/form-data")
    public AppointmentRequestDto saveAppointment( @Valid @ModelAttribute AppointmentRequestDto appointmentRequestDto,
                                                 @RequestPart("file") MultipartFile file) {

        iFileService.storeFile(file);

        String fileAttach = file.getOriginalFilename();

        if (ObjectUtils.isEmpty(fileAttach)) {
            throw new FileStorageException("file is empty");
        }
        appointmentRequestDto.setFileAttach(fileAttach);
        return iAppointmentService.saveAppointment(appointmentRequestDto);
    }

    //To get the file -
    @GetMapping("/images/{appointmentId}")
    public ResponseEntity<AppointmentRequestDto> serveUserImages(@PathVariable Long appointmentId, HttpServletResponse response) throws IOException, BusinessException {
        AppointmentRequestDto appointment = this.iAppointmentService.getAppointment(appointmentId);
        String fileName = appointment.getFileAttach();
        System.out.println("hello1");
        System.out.println("Appointment : "+appointment);

            //use of InputStream because file bytes me hogi...
            InputStream resource = this.iFileService.getResource("image/", fileName);

            //without this, status 200 aayega but to get image, we need this.
            StreamUtils.copy(resource, response.getOutputStream());
            return ResponseEntity.ok(appointment);
    }


}
