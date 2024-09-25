package com.doctor.controller;

import com.doctor.dto.HospitalAddressDto;
import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalAddress;
import com.doctor.service.DoctorServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/hospitaladdress/")
public class HospitalAddressController {
    @Autowired
    private DoctorServices doctorServices;

    @GetMapping("{addressId}")
    public ResponseEntity<HospitalAddressDto> getDoctorById(@PathVariable Long addressId) {
        HospitalAddressDto hospitalAddressById = doctorServices.getHospitalAddressById(addressId);
        return new ResponseEntity<>(hospitalAddressById, HttpStatus.OK);

    }
}

