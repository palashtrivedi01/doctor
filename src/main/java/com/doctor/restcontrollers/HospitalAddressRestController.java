package com.doctor.restcontrollers;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.HospitalAddressRequestDto;
import com.doctor.services.IHospitalAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/hms/hospitalAddress")
public class HospitalAddressRestController {

    @Autowired
    private IHospitalAddressService iHospitalAddressService;

    @PostMapping("/saveHospital")
    public ResponseEntity<HospitalAddressRequestDto> saveHospitalAddress(@Valid @RequestBody HospitalAddressRequestDto hospitalAddressRequestDto) {
        return new ResponseEntity<>(this.iHospitalAddressService.saveHospitalAddress(hospitalAddressRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateHospitalByHospitalId/{hospitalId}")
    public ResponseEntity<HospitalAddressRequestDto> updateHospitalByHospitalId(@PathVariable Long hospitalId, @Valid @RequestBody HospitalAddressRequestDto hospitalAddressRequestDto) throws BusinessException {
        return ResponseEntity.ok(this.iHospitalAddressService.updateHospitalByHospitalId(hospitalId, hospitalAddressRequestDto));
    }

    @DeleteMapping("/deleteHospitalAddressByHospitalId/{hospitalId}")
    public ResponseEntity<String> deleteHospitalAddressByHospitalId(@PathVariable Long hospitalId) throws BusinessException {
        return new ResponseEntity<>(this.iHospitalAddressService.deleteHospitalAddressByHospitalId(hospitalId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getHospitalAddressById/{hospitalId}")
    public ResponseEntity<HospitalAddressRequestDto> getHospitalAddressById(@PathVariable Long hospitalId) throws BusinessException {
        return ResponseEntity.ok(this.iHospitalAddressService.getHospitalAddressById(hospitalId));
    }

    @GetMapping("/getAllHospitalAddresses")
    public ResponseEntity<List<HospitalAddressRequestDto>> getAllHospitalAddresses() {
        return ResponseEntity.ok(this.iHospitalAddressService.getAllHospitalAddresses());
    }

    @GetMapping("/getAllHospitalAddressesByCity/{city}")
    public ResponseEntity<List<HospitalAddressRequestDto>> getAllHospitalAddressesByCity(@PathVariable String city) throws BusinessException {
        return new ResponseEntity<>(this.iHospitalAddressService.getAllHospitalAddressesByCity(city), HttpStatus.OK);
    }

    @GetMapping("/getAllHospitalAddressesByCountry")
    public ResponseEntity<List<HospitalAddressRequestDto>> getAllHospitalAddressesByCountry(String country) throws BusinessException {
        return new ResponseEntity<>(this.iHospitalAddressService.getAllHospitalAddressesByCountry(country), HttpStatus.OK);
    }

    @GetMapping("/getAllHospitalAddressesByState")
    public ResponseEntity<List<HospitalAddressRequestDto>> getAllHospitalAddressesByState(String state) throws BusinessException {
        return new ResponseEntity<>(this.iHospitalAddressService.getAllHospitalAddressesByState(state), HttpStatus.OK);
    }

}
