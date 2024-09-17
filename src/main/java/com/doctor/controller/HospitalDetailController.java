package com.doctor.controller;

import com.doctor.exception.BusinessException;
import com.doctor.requestdto.HospitalDetailsRequestDto;
import com.doctor.services.HospitalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalDetailController {

    @Autowired
    private HospitalDetailsService hospitalDetailsService;

    @PostMapping("/addHospital")
    public ResponseEntity<HospitalDetailsRequestDto> addHospital(@RequestBody HospitalDetailsRequestDto hospitalDetailsRequestDto) throws BusinessException {
        HospitalDetailsRequestDto hospitalDetailsRequestDto1 = hospitalDetailsService.addHospitalDetails(hospitalDetailsRequestDto);
        return new ResponseEntity<>(hospitalDetailsRequestDto1, HttpStatus.OK);

    }

    @GetMapping("/{hospitalId}")
    public ResponseEntity<HospitalDetailsRequestDto> getHospitalById(@PathVariable Long hospitalId) throws BusinessException {
        HospitalDetailsRequestDto hospitalDetailsById = hospitalDetailsService.getHospitalDetailsById(hospitalId);
        return new ResponseEntity<>(hospitalDetailsById, HttpStatus.OK);
    }

    @GetMapping("/allHospital")
    public ResponseEntity<List<HospitalDetailsRequestDto>> getAllHospitals() throws BusinessException {
        List<HospitalDetailsRequestDto> allHospitalDetails = hospitalDetailsService.getAllHospitalDetails();
        return new ResponseEntity<>(allHospitalDetails, HttpStatus.OK);
    }

    @GetMapping("/hospitalName/{hospitalName}")
    public ResponseEntity<HospitalDetailsRequestDto> findHospitalByName(@PathVariable String hospitalName) throws BusinessException {
        HospitalDetailsRequestDto hospitalByName = hospitalDetailsService.getHospitalByName(hospitalName);
        return new ResponseEntity<>(hospitalByName, HttpStatus.OK);

    }


}
