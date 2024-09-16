package com.doctor.services;

import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.requestdto.DoctorRequestDto;

import java.util.List;

public interface DoctorService {

    DoctorRequestDto addDoctor(DoctorRequestDto doctorRequestDto);

    DoctorRequestDto updateDoctor(DoctorRequestDto doctorRequestDto, String doctorEmail) throws BusinessException;

    DoctorRequestDto getDoctorByDoctorEmail(String doctorEmail) throws ControllerException;

    DoctorRequestDto getDoctorByDoctorId(Long doctorId) throws ControllerException;

    List<DoctorRequestDto> getAllDoctors() throws ControllerException;

    String deleteDoctor(Long doctorId) throws ControllerException;

    List<AppointmentRequestDto> getAppointmentByDoctorEmail(String doctorEmail) throws BusinessException;

}
