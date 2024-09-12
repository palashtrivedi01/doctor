package com.doctor.serviceimpl;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.exception.InvalidInputException;
import com.doctor.repository.AppointmentRepo;
import com.doctor.repository.DoctorRepo;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.requestdto.DoctorRequestDto;
import com.doctor.services.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DoctorRequestDto addDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = modelMapper.map(doctorRequestDto, Doctor.class);
        Doctor doctor1 = doctorRepo.save(doctor);
        DoctorRequestDto doctorRequestDto1 = new DoctorRequestDto();
        modelMapper.map(doctor, doctorRequestDto1);
        return doctorRequestDto1;
    }

    @Override
    public DoctorRequestDto updateDoctor(DoctorRequestDto doctorRequestDto, String doctorEmail) throws BusinessException {
        Optional<Doctor> byDoctorEmail = doctorRepo.findByDoctorEmail(doctorEmail);
        if (byDoctorEmail.isPresent()) {
            Doctor doctor1 = byDoctorEmail.get();
            doctor1.setDoctorName(doctorRequestDto.getDoctorName());
            doctor1.setDoctorGender(doctorRequestDto.getDoctorGender());
            doctor1.setDoctorMobileNumber(doctorRequestDto.getDoctorMobileNumber());
            doctor1.setDoctorPassword(doctorRequestDto.getDoctorPassword());
            doctor1.setDoctorSpecialization(doctorRequestDto.getDoctorSpecialization());
            doctor1.setDoctorEmail(doctorEmail);
            Doctor save = doctorRepo.save(doctor1);
            DoctorRequestDto doctorRequestDto1 = new DoctorRequestDto();
            modelMapper.map(doctor1, doctorRequestDto1);
            return doctorRequestDto1;
        } else {
            throw new BusinessException("Doctor not found with given Doctor Email : " + doctorEmail);
        }
    }

    @Override
    public DoctorRequestDto getDoctorByDoctorEmail(String doctorEmail) throws ControllerException {
        Optional<Doctor> byDoctorEmail = doctorRepo.findByDoctorEmail(doctorEmail);
        if (byDoctorEmail.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            Doctor doctor = modelMapper.map(byDoctorEmail.get(), Doctor.class);
            DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
            modelMapper.map(doctor, doctorRequestDto);
            return doctorRequestDto;
        }
        throw new ControllerException("Doctor not found with given Doctor Email : " + doctorEmail);
    }

    @Override
    public DoctorRequestDto getDoctorByDoctorId(Long doctorId) throws ControllerException {
        if (doctorRepo.existsById(doctorId)) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(doctorRepo.findById(doctorId).get(), DoctorRequestDto.class);
        } else {
            throw new ControllerException("Doctor not found with given Doctor Id: " + doctorId);
        }

    }

    @Override
    public List<DoctorRequestDto> getAllDoctors() throws ControllerException {
        List<Doctor> allDoctors = doctorRepo.findAll();
        List<DoctorRequestDto> collect = allDoctors.stream().map(doctor -> modelMapper.map(doctor, DoctorRequestDto.class)).collect(Collectors.toList());
        if (allDoctors.isEmpty()) {
            throw new ControllerException("No Doctor available. ");
        }


        return collect;
    }

    @Override
    public String deleteDoctor(Long doctorId) throws ControllerException {
        if (!doctorRepo.existsById(doctorId)) {
            throw new ControllerException("Doctor not found Exception with  given Doctor Id : " + doctorId);
        }
        doctorRepo.deleteById(doctorId);
        return "Doctor deleted successfully";

    }


    @Override
    public List<AppointmentRequestDto> getAppointmentByDoctorEmail(String doctorEmail) throws BusinessException {
        Optional<Doctor> doctor = doctorRepo.findByDoctorEmail(doctorEmail);
        Optional<Appointment> appointment = appointmentRepo.findByDoctorEmail(doctorEmail);


        if (doctor.isPresent() && appointment.isPresent()) {
            List<AppointmentRequestDto> list = appointment.stream().map(e -> modelMapper.map(e, AppointmentRequestDto.class)).toList();
            return list;
        }
        else {
            throw new BusinessException("Doctor not found Exception with given email : "+ doctorEmail);

        }
    }



}
