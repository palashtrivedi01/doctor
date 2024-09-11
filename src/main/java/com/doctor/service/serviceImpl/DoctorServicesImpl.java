package com.doctor.service.serviceImpl;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.entities.Doctor;
import com.doctor.exception.ControllerException;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.repository.DoctorRepository;
import com.doctor.service.DoctorServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServicesImpl implements DoctorServices {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Doctor addDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = modelMapper.map(doctorRequestDto,Doctor.class);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(String doctorEmail, DoctorRequestDto doctorRequestDto) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByDoctorEmail(doctorEmail);

        if (optionalDoctor.isPresent()){
            Doctor doctor = optionalDoctor.get();
            modelMapper.map(doctorRequestDto,doctor);
            return doctorRepository.save(doctor);
        }else
            return null;
//            throw new DoctorNotFoundException("Doctor not found with email :"+ doctorEmail);
    }

    @Override
    public Optional<Doctor> getDoctorById(long doctorId) {
        return  this.doctorRepository.findById(doctorId);
    }


    @Override
    public Doctor getDoctorByEmail(String doctorEmail) throws DoctorNotFoundException {
        return doctorRepository.findByDoctorEmail(doctorEmail)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with email: " + doctorEmail));
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


}


