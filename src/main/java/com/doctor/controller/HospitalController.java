package com.doctor.controller;

import com.doctor.dto.HospitalDetailsDto;
import com.doctor.entities.HospitalDetails;
import com.doctor.service.HospitalDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/")
@RestController("/hospital")
public class HospitalController {



    @Autowired
    private HospitalDetailInterface hospitalDetail;

    @PostMapping("/hospital/addhospital")
    public ResponseEntity<HospitalDetailsDto> hospitalDetailsDto(@RequestBody HospitalDetailsDto hospitalDetailsDto) {
    HospitalDetailsDto addHospital= (HospitalDetailsDto) this.hospitalDetail.addNewHospital(hospitalDetailsDto);
    return ResponseEntity.ok(addHospital);

    }

    @GetMapping("hospital/{hospitalId}")
    public  ResponseEntity<HospitalDetailsDto> getHospital(@PathVariable Long hospitalId) {

        HospitalDetailsDto getHospitalById=this.hospitalDetail.getHospitalDetailsByHospitalId(hospitalId);
        return ResponseEntity.ok(getHospitalById);
    }


    @GetMapping("/hospital/allhospital")
    public ResponseEntity<List<HospitalDetails>> getAllHospital() {

        List<HospitalDetails> getAll=this.hospitalDetail.getAllHospitals();
        return ResponseEntity.ok(getAll);
    }
@GetMapping("/hospital/hosptailname/{hospitalName}")
    public ResponseEntity<List<HospitalDetails>> getHospitalByHospitalName(@PathVariable String hospitalName) {
        List<HospitalDetails> getByName=this.hospitalDetail.getAllHospitalsByName(hospitalName);
        return ResponseEntity.ok(getByName);
}
}