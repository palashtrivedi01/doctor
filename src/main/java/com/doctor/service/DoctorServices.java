package com.doctor.service;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.entities.Doctor;
import com.doctor.exception.DoctorNotFoundException;

import  java.util.List;
import java.util.Optional;

public interface DoctorServices {

    Doctor addDoctor(DoctorRequestDto doctorRequestDto);

    Doctor updateDoctor(String email, DoctorRequestDto doctorRequestDto) ;

    Optional<Doctor> getDoctorById(long doctorId);
    Doctor getDoctorByEmail(String doctorEmail) throws DoctorNotFoundException;
    List<Doctor> getAllDoctors();
    void deleteDoctorById(Long doctorId) ;
}
