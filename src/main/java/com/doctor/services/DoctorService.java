package com.doctor.services;


import com.doctor.entity.Appointment;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.exception.InvalidInputException;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.requestdto.DoctorRequestDto;

import java.util.List;

public interface DoctorService {



    public DoctorRequestDto addDoctor(DoctorRequestDto doctorRequestDto);



    public DoctorRequestDto updateDoctor(DoctorRequestDto doctorRequestDto, String doctorEmail) throws BusinessException;

    public DoctorRequestDto getDoctorByDoctorEmail(String doctorEmail) throws  ControllerException;

    public DoctorRequestDto getDoctorByDoctorId(Long doctorId) throws  ControllerException;
    public List<DoctorRequestDto> getAllDoctors() throws ControllerException;

    public String deleteDoctor(Long doctorId) throws ControllerException;


    public List<AppointmentRequestDto> getAppointmentByDoctorEmail(String doctorEmail) throws BusinessException;

}
