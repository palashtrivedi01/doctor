package com.doctor.restcontrollers;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.DoctorRequestDto;
import com.doctor.services.IAppointmentService;
import com.doctor.services.IDoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {

    @Autowired
    private IDoctorService iDoctorService;

    @Autowired
    private IAppointmentService iAppointmentService;

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("CONTROLLER IS WORKING", HttpStatus.ACCEPTED);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<DoctorRequestDto> addDoctor(@Validated @RequestBody DoctorRequestDto doctorRequestDto){
        return new ResponseEntity<>(this.iDoctorService.saveDoctor(doctorRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateDoctor/{doctorEmail}")
    public ResponseEntity<DoctorRequestDto> updateDoctor(@PathVariable String doctorEmail, @RequestBody DoctorRequestDto doctorRequestDto) throws BusinessException {
        return new ResponseEntity<>(this.iDoctorService.updateDoctor(doctorEmail, doctorRequestDto), HttpStatus.OK);
    }

    @GetMapping("/getDoctorByDoctorId/{doctorId}")
    public ResponseEntity<DoctorRequestDto> getDoctorByDoctorId(@PathVariable Long doctorId){
        return new ResponseEntity<>(this.iDoctorService.getDoctorByDoctorId(doctorId), HttpStatus.OK);
    }

    @GetMapping("/doctoremail/{doctorEmail}")
    public ResponseEntity<DoctorRequestDto> getDoctorByEmail(@PathVariable("doctorEmail") String email) throws BusinessException{
       return new ResponseEntity<>(this.iDoctorService.getDoctorByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/allDoctor")
    public ResponseEntity<List<DoctorRequestDto>> getAllDoctors(){
        return new ResponseEntity<>(this.iDoctorService.getAllDoctors(), HttpStatus.OK);
    }

    @DeleteMapping("/deletedoctorById/{doctorId}")
    public ResponseEntity<String> deleteDoctorByDoctorId(@PathVariable Long doctorId) throws BusinessException {
        this.iDoctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>("DOCTOR DELETED SUCCESSFULLY", HttpStatus.OK);
    }

    @GetMapping("/getAppointments/{doctorEmail}")
    public ResponseEntity<?> getAppointmentsByDoctorEmail(@PathVariable("doctorEmail") String doctorEmail) throws BusinessException{
        return new ResponseEntity<>(this.iDoctorService.findAppointmentsByDoctorEmail(doctorEmail), HttpStatus.OK);
    }


}
