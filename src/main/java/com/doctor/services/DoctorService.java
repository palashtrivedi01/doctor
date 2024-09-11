package com.doctor.services;


import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.exception.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {

    public Doctor addDoctor(Doctor doctor);

    public Doctor updateDoctor(Doctor doctor,String doctorEmail) throws DoctorNotFoundException;
    public Doctor getDoctorByDoctorEmail(String doctorEmail) throws DoctorNotFoundException;

    public Doctor getDoctorByDoctorId(Long doctorId) throws DoctorNotFoundException;
    public List<Doctor> getAllDoctors();

    public String deleteDoctor(Long doctorId) throws DoctorNotFoundException;

    public List<Appointment> getAppointmentByDoctorEmail(String doctorEmail) throws DoctorNotFoundException;

}
