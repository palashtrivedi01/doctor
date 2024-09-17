package com.doctor.restcontrollers;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.HospitalDetailsRequestDto;
import com.doctor.services.IHospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/hms/hospital")
public class HospitalDetailsRestController {

    @Autowired
    private IHospitalService iHospitalService;

    @PostMapping("/addHospital")
    public ResponseEntity<HospitalDetailsRequestDto> addHospital(@Valid @RequestBody HospitalDetailsRequestDto hospitalDetailsRequestDto) {
        return ResponseEntity.ok(this.iHospitalService.addHospital(hospitalDetailsRequestDto));
    }

    @PutMapping("/updateHospital/{hospitalId}")
    public ResponseEntity<HospitalDetailsRequestDto> updateHospital(@Valid @RequestBody HospitalDetailsRequestDto hospitalDetailsRequestDto, @PathVariable Long hospitalId) throws BusinessException {
        return ResponseEntity.ok(this.iHospitalService.updateHospital(hospitalDetailsRequestDto, hospitalId));
    }

    @DeleteMapping("/deleteHospitalByHospitalId/{hospitalId}")
    public ResponseEntity<String> deleteHospitalByHospitalId(@PathVariable Long hospitalId) throws BusinessException {
        return ResponseEntity.ok(this.iHospitalService.deleteHospitalByHospitalId(hospitalId));
    }

    @GetMapping("/getHospitalByHospitalId/{hospitalId}")
    public ResponseEntity<HospitalDetailsRequestDto> getHospitalByHospitalId(@PathVariable Long hospitalId) throws BusinessException {
        return ResponseEntity.ok(this.iHospitalService.getHospitalByHospitalId(hospitalId));
    }

    @GetMapping("/getAllHospitals")
    public ResponseEntity<List<HospitalDetailsRequestDto>> getAllHospitals() {
        return ResponseEntity.ok(this.iHospitalService.getAllHospitals());
    }

    @GetMapping("/getAllHospitalsByHospitalName/{hospitalName}")
    public ResponseEntity<List<HospitalDetailsRequestDto>> getAllHospitalsByHospitalName(@RequestParam("hospitalName") String hospitalName) throws BusinessException {
        return ResponseEntity.ok(this.iHospitalService.getAllHospitalsByHospitalName(hospitalName));
    }

}
