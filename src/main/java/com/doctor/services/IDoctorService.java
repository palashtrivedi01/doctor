package com.doctor.services;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.requestDto.DoctorRequestDto;
import java.util.List;

public interface IDoctorService {

    DoctorRequestDto saveDoctor(DoctorRequestDto doctorRequestDto);

    DoctorRequestDto updateDoctor(String doctorEmail, DoctorRequestDto doctorRequestDto) throws BusinessException;

    String deleteDoctor(Long doctorId) throws BusinessException ;

    DoctorRequestDto getDoctorByDoctorId(Long doctorId);

    List<DoctorRequestDto> getAllDoctors();

    DoctorRequestDto getDoctorByEmail(String doctorEmail) throws BusinessException;

    List<AppointmentRequestDto> findAppointmentsByDoctorEmail(String doctorEmail) throws BusinessException;

}
