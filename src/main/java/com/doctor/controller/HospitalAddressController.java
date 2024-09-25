package com.doctor.controller;

import com.doctor.dto.HospitalAddressDto;
import com.doctor.repository.DoctorRepository;
import com.doctor.repository.HospitalAddressRepository;
import com.doctor.service.serviceimpl.HelloDoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitalAddress/")
public class HospitalAddressController {

    @Autowired
    private HelloDoctorServices helloDoctorServices;

    @Autowired
    private DoctorRepository doctorRepository;


    @Autowired
    private HospitalAddressRepository hospitalAddressRepository;

    @GetMapping("/{addressId}")
    public HospitalAddressDto getHospitalAddressById(@PathVariable Long addressId) {
        HospitalAddressDto hospitalAddressById = this.helloDoctorServices.getHospitalAddressById(addressId);
        return hospitalAddressById;


    }
}
