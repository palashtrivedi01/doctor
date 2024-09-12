package com.doctor.services;


import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;

import java.util.List;

public interface DoctorService {

    public Doctor addDoctor(Doctor doctor);

    public Doctor updateDoctor(Doctor doctor,String doctorEmail) throws BusinessException;
    public Doctor getDoctorByDoctorEmail(String doctorEmail) throws  ControllerException;

    public Doctor getDoctorByDoctorId(Long doctorId) throws  ControllerException;
    public List<Doctor> getAllDoctors() throws ControllerException;

    public String deleteDoctor(Long doctorId) throws ControllerException;

    public List<Appointment> getAppointmentByDoctorEmail(String doctorEmail) throws BusinessException;

}
