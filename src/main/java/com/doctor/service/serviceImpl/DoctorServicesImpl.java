package com.doctor.service.serviceImpl;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.dto.HospitalAddressDto;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.entities.HospitalAddress;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.repository.AppointmentRepository;
import com.doctor.repository.DoctorRepository;

import com.doctor.repository.HospitalAddressRepository;
import com.doctor.service.DoctorServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServicesImpl implements DoctorServices {

    private static final Logger log = LoggerFactory.getLogger(DoctorServicesImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalAddressRepository hospitalAddressRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;

    @Override
    public Doctor addDoctor(DoctorRequestDto doctorRequestDto) {
       Doctor doctor = new Doctor();
       doctor.setDoctorGender(doctorRequestDto.getDoctorGender());
       doctor.setDoctorEmail(doctorRequestDto.getDoctorEmail());
       doctor.setDoctorName(doctorRequestDto.getDoctorName());
       doctor.setDoctorPassword(doctorRequestDto.getDoctorPassword());
       doctor.setDoctorSpecialization(doctorRequestDto.getDoctorSpecialization());
       doctor.setDoctorName(doctorRequestDto.getDoctorName());
       doctor.setDoctorMobileNumber(doctorRequestDto.getDoctorMobileNumber());

        doctorRepository.save(doctor);
        return doctor;

    }


    @Override
    public Doctor updateDoctor(String doctorEmail, DoctorRequestDto doctorRequestDto) throws DoctorNotFoundException {
        if (doctorEmail==null || doctorEmail.isEmpty()){
            throw  new ControllerException("Email cannot be null ");
        }
        Optional<Doctor> optionalDoctor = Optional.ofNullable(doctorRepository.findByDoctorEmail(doctorEmail));
        log.info("All the updated data of the doctor{}", optionalDoctor);
        if (optionalDoctor.isPresent()){
            Doctor doctor = optionalDoctor.get();
            return doctorRepository.save(doctor);
        }else
            throw new DoctorNotFoundException("Doctor not found with email :"+ doctorEmail);
    }

    @Override
    public Optional<Doctor> getDoctorById(long doctorId) {
        if (doctorId <= 0) {
            throw new ControllerException("Invalid id");
        }
        return this.doctorRepository.findById(doctorId);
    }


    @Override
    public Doctor getDoctorByEmail(String doctorEmail) throws DoctorNotFoundException {
        if (doctorEmail==null || doctorEmail.isEmpty()){
            throw  new ControllerException("Email cannot be null ");
        }
        return doctorRepository.findByDoctorEmail(doctorEmail);
//                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with email: " + doctorEmail));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public void deleteDoctorById(Long doctorId) {
        if (doctorRepository.existsById(doctorId)) {
            doctorRepository.deleteById(doctorId);
        } else {
            throw new ControllerException("Doctor not found with ID: " + doctorId);
        }
    }

    @Override
    public List<Appointment> getAppoinmentByDoctorEmail(String doctorEmail) {
        Doctor doctor = doctorRepository.findByDoctorEmail((doctorEmail));
        return appointmentRepository.findByDoctorEmail((doctorEmail));
    }

    @Override
    public HospitalAddressDto getHospitalAddressById(Long addressId) {
        HospitalAddress address = hospitalAddressRepository.findById(addressId).orElseThrow(() -> new BusinessException("Invalid Input"));
        HospitalAddressDto hospitalAddressDto= new HospitalAddressDto();
        hospitalAddressDto.setAddressName(hospitalAddressDto.getAddressName());
        hospitalAddressDto.setCity(hospitalAddressDto.getCity());
        hospitalAddressDto.setState(hospitalAddressDto.getAddressName());
        hospitalAddressDto.setZipCode(hospitalAddressDto.getZipCode());
        return hospitalAddressDto;
    }


}