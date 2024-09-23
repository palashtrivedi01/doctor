package com.doctor.service;

import com.doctor.dto.DoctorRequestDTO;
import com.doctor.dto.HospitalAddressDto;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalAddress;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repository.AppointmentRepository;
import com.doctor.repository.DoctorRepository;
import com.doctor.repository.HospitalAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelloDoctorServices implements DoctorServiceInterface {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    private HospitalAddressRepository hospitalAddressRepository;



    @Override
    public DoctorRequestDTO addDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = new Doctor();
        doctor.setDoctorGender(doctorRequestDTO.getDoctorGender());
        doctor.setDoctorName(doctorRequestDTO.getDoctorName());
        doctor.setDoctorSpecialization(doctorRequestDTO.getDoctorSpecialization());
        doctor.setDoctorPassword(doctorRequestDTO.getDoctorPassword());
        doctor.setHospitalName(doctorRequestDTO.getHospitalName());
        doctor.setDoctorMobileNumber(doctorRequestDTO.getDoctorMobileNumber());
        doctor.setDoctorEmail(doctorRequestDTO.getDoctorEmail());

         doctorRepository.save(doctor);
         return doctorRequestDTO;

    }

    @Override
    public Doctor updateDoctor(String doctorEmail, Doctor doctor) {
        Doctor exisistingDoctor = doctorRepository.findByDoctorEmail(doctorEmail);
        exisistingDoctor.setDoctorName(doctor.getDoctorName());
        exisistingDoctor.setDoctorEmail(doctor.getDoctorEmail());
        System.out.println(exisistingDoctor.getDoctorEmail());

        this.doctorRepository.save(exisistingDoctor);

        if (doctorEmail == null || doctorEmail.isEmpty()) {
            throw new ControllerException("Wrong entry");

        }

        return exisistingDoctor;
    }

    @Override
    public Optional<Doctor> findDoctorById(Long doctorId) {
        Optional<Doctor> doctor = this.doctorRepository.findById(doctorId);

        if (doctor.isEmpty()) {
            throw new ControllerException("Id not found");
        }

        return doctor;
    }

    @Override
    public Doctor findByDoctorEmail(String doctorEmail) {
        Doctor doctor = this.doctorRepository.findByDoctorEmail(doctorEmail);

        if (doctorEmail == null || doctorEmail.isEmpty()) {
            throw new ControllerException("Wrong entry");

        } else
            return doctor;

    }

    @Override
    public List<Doctor> findAllDoctor() {
        List<Doctor> doctors = this.doctorRepository.findAll();

        if (doctors.isEmpty()) {
            throw new ControllerException("No doctor found");
        }

        return doctors;
    }

    @Override
    public String deleteByDoctorId(Long doctorId) {
        if (doctorId == null) {
            throw new ControllerException("Id not found");
        } else {
            Doctor doctor;
            this.doctorRepository.deleteById(doctorId);
        }
        return "deleted";
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorEmail(String doctorEmail) {
        List<Appointment> appointment = this.appointmentRepository.findByDoctorEmail(doctorEmail);
        return appointment;
    }


    @Override
    public HospitalAddressDto getHospitalAddressById(Long addressId) {
        HospitalAddress byId = hospitalAddressRepository.findById(addressId).orElseThrow(() -> new BusinessException("Invalid input"));
        if (byId != null) {
            HospitalAddressDto hospitalAddressDto = new HospitalAddressDto();
            hospitalAddressDto.setAddressId(byId.getAddressId());
            hospitalAddressDto.setAddressName(byId.getAddressName());
            hospitalAddressDto.setCity(byId.getCity());
            hospitalAddressDto.setCountry(byId.getCountry());
            hospitalAddressDto.setState(byId.getState());
            hospitalAddressDto.setZipCode(byId.getZipCode());
            return hospitalAddressDto;
        }
        throw new ControllerException("Invalid input");

    }


}



