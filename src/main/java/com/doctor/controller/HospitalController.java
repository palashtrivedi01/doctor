package com.doctor.controller;

import com.doctor.dto.HospitalDto;
import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalDetails;
import com.doctor.service.HospitalDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospital/")
public class HospitalController {
    @Autowired
    private HospitalDetailService hospitalDetailService;


    @PostMapping("addhospital")
    public ResponseEntity<HospitalDto> addDoctor(@RequestBody HospitalDto hospitalDto) {
        HospitalDetails hospitalDto1 = hospitalDetailService.addHospital(hospitalDto);
        return new ResponseEntity<>(hospitalDto,HttpStatus.OK);
    }
    @GetMapping("{hospitalId}")
    public ResponseEntity<HospitalDto> hospitalById(@PathVariable Long hospitalId){
        HospitalDto hospitalDetails = hospitalDetailService.getHopitalById(hospitalId);
        return new ResponseEntity<>(hospitalDetails,HttpStatus.OK);

    }
    @GetMapping("/allhospital")
    public ResponseEntity<List<HospitalDetails>> getAllHospital() {
        List<HospitalDetails> doctors = hospitalDetailService.getAllHospital();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
   }
//    @GetMapping("hosptailname/{hospitalName}")
//    public ResponseEntity<List<HospitalDto>> findHospitalByName(@PathVariable String hospitalName){
//        List<HospitalDto> hospitalDtos = hospitalDetailService.getHospitalByName(hospitalName);
//        return new ResponseEntity<>(hospitalDtos,HttpStatus.OK);
//    }


}
