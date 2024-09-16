package com.doctor.controller;

import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.requestdto.HospitalAddressRequestDto;
import com.doctor.services.HospitalAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitalAddress/")
public class HospitalAddressController {

    @Autowired
    private HospitalAddressService hospitalAddressService;

    @GetMapping("/{addressId}")
    public ResponseEntity<HospitalAddressRequestDto> getHospitalAddressById(@PathVariable("addressId") Long addressId) throws BusinessException, ControllerException {
        HospitalAddressRequestDto hospitalAddressById = hospitalAddressService.getHospitalAddressById(addressId);
        return new ResponseEntity<>(hospitalAddressById, HttpStatus.OK);
    }

}
