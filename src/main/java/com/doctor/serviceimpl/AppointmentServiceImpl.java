package com.doctor.serviceimpl;

import com.doctor.entity.Appointment;
import com.doctor.exception.InvalidInputException;
import com.doctor.repository.AppointmentRepo;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.services.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto) {
        System.out.println(appointmentRequestDto);
        Appointment appointment = modelMapper.map(appointmentRequestDto, Appointment.class);
        Appointment appointment1 = appointmentRepo.save(appointment);
        AppointmentRequestDto map = modelMapper.map(appointment1, AppointmentRequestDto.class);
        System.out.println(map);
        return map;

    }

    @Override
    public AppointmentRequestDto findByPatientEmail(String patientEmail) throws InvalidInputException {
        Optional<Appointment> optionalAppointment = appointmentRepo.findByPatientEmail(patientEmail);
        if (optionalAppointment.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(optionalAppointment.get(), AppointmentRequestDto.class);

//            return optionalAppointment.get();
        } else if (!optionalAppointment.equals(patientEmail)) {
            throw new InvalidInputException("Invalid input Exception");
        } else {
            throw new InvalidInputException("Input is Empty !!!!");
        }


    }


}