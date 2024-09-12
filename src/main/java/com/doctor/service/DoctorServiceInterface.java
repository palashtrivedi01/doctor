package com.doctor.service;

import com.doctor.dto.DoctorRequestDTO;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorServiceInterface {

   public Doctor addDoctor(Doctor doctor);
   // public DoctorRequestDTO addDoctor(Doctor doctor);
    public Doctor updateDoctor(String doctorEmail, Doctor doctor);

    public Optional<Doctor> findDoctorById(Long doctorId);

    Doctor findByDoctorEmail(String doctorEmail);

    List<Doctor> findAllDoctor();

  String deleteByDoctorId(Long doctorId);

    List<Appointment> getAppointmentsByDoctorEmail(String doctorEmail);

}
