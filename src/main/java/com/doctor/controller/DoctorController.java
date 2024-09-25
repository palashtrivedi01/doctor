package com.doctor.controller;

import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.requestdto.DoctorRequestDto;
import com.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity<String> homeEndpoint() {
        return new ResponseEntity<>("welcome home", HttpStatus.OK);

    }

    @PostMapping("/addDoctor")
    public ResponseEntity<DoctorRequestDto> addDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {
        DoctorRequestDto doctor1 = doctorService.addDoctor(doctorRequestDto);
        return new ResponseEntity<>(doctor1, HttpStatus.OK);
    }

    @PutMapping("/updateDoctor/{doctorEmail}")
    public ResponseEntity<DoctorRequestDto> updateDoctor(@RequestBody DoctorRequestDto doctorRequestDto, @PathVariable String doctorEmail) throws BusinessException {
        DoctorRequestDto dto = doctorService.updateDoctor(doctorRequestDto, doctorEmail);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    @GetMapping("/doctorEmail/{doctorEmail}")
    public ResponseEntity<DoctorRequestDto> getDoctorByDoctorEmail(@PathVariable String doctorEmail) throws ControllerException {
        DoctorRequestDto byDoctorEmail = doctorService.getDoctorByDoctorEmail(doctorEmail);
        return new ResponseEntity<>(byDoctorEmail, HttpStatus.OK);
    }

    @GetMapping("doctorId/{doctorId}")
    public ResponseEntity<DoctorRequestDto> getDoctorById(@PathVariable Long doctorId) throws  ControllerException {
        DoctorRequestDto doctorByDoctorId = doctorService.getDoctorByDoctorId(doctorId);
        return new ResponseEntity<>(doctorByDoctorId, HttpStatus.OK);
    }

    @GetMapping("/allDoctor")
    public ResponseEntity<List<DoctorRequestDto>> getAllDoctors() throws ControllerException {
        List<DoctorRequestDto> allDoctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);
    }

    @DeleteMapping("deleteDoctorById/{doctorId}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Long doctorId) throws ControllerException {
        String s = doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/getAppointment/{doctorEmail}")
    public ResponseEntity<List<AppointmentRequestDto>> getAppointmentByDoctorEmail(@PathVariable String doctorEmail) throws BusinessException {
        List<AppointmentRequestDto> appointmentByDoctorEmail = doctorService.getAppointmentByDoctorEmail(doctorEmail);
        return ResponseEntity.ok(appointmentByDoctorEmail);

    }


}
