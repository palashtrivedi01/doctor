package com.doctor.services;

import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.requestDto.DoctorRequestDto;
import java.util.List;

public interface IDoctorService {

    DoctorRequestDto saveDoctor(DoctorRequestDto doctorRequestDto);

    DoctorRequestDto updateDoctor(String doctorEmail, DoctorRequestDto doctorRequestDto);

    String deleteDoctor(Long doctorId);

    DoctorRequestDto getDoctorByDoctorId(Long doctorId);

    List<DoctorRequestDto> getAllDoctors();

    DoctorRequestDto getDoctorByEmail(String doctorEmail);

    List<AppointmentRequestDto> findAppointmentsByDoctorEmail(String doctorEmail);

}
