package com.doctor.service;

import com.doctor.dto.DoctorRequestDTO;
import com.doctor.dto.HospitalAddressDto;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;
@Mapper(componentModel = "spring")
public interface DoctorServiceInterface {

   public DoctorRequestDTO addDoctor(DoctorRequestDTO doctorRequestDTO);
//    public DoctorRequestDTO addDoctor(DoctorRequestDTO doctorRequestDTO);
//
//    DoctorRequestDTO DoctorToDoctorRequestDto(Doctor doctor);
//
//    Doctor DoctorRequestdtoToDoctor(DoctorRequestDTO doctorRequestDTO);

    public Doctor updateDoctor(String doctorEmail, Doctor doctor);

    public Optional<Doctor> findDoctorById(Long doctorId);

    Doctor findByDoctorEmail(String doctorEmail);

    List<Doctor> findAllDoctor();

  String deleteByDoctorId(Long doctorId);

    List<Appointment> getAppointmentsByDoctorEmail(String doctorEmail);


    HospitalAddressDto getHospitalAddressById(Long addressId);
}
