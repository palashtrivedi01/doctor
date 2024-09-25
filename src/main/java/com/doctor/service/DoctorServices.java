package com.doctor.service;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.dto.HospitalAddressDto;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalAddress;
import com.doctor.exception.DoctorNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Optional;

@FeignClient(name="patient")
public interface DoctorServices {

    Doctor addDoctor(DoctorRequestDto doctorRequestDto);

    Doctor updateDoctor(String email, DoctorRequestDto doctorRequestDto) throws DoctorNotFoundException;

    Optional<Doctor> getDoctorById(long doctorId);

    Doctor getDoctorByEmail(String doctorEmail) throws DoctorNotFoundException;

    List<Doctor> getAllDoctors();

    void deleteDoctorById(Long doctorId);

    List<Appointment> getAppoinmentByDoctorEmail(String doctorEmail);

    HospitalAddressDto getHospitalAddressById(Long addressId);



}
