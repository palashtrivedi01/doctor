package com.doctor.serviceImpl;

import com.doctor.entities.Appointment;
import com.doctor.repositories.IAppointmentRepository;
import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.requestDto.DoctorRequestDto;
import com.doctor.services.IAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto) {

        Appointment appointment = modelMapper.map(appointmentRequestDto, Appointment.class);

       Appointment appointment1 = iAppointmentRepository.save(appointment);

       AppointmentRequestDto newAppointmentRequestDto = modelMapper.map(appointment1, AppointmentRequestDto.class);
       return newAppointmentRequestDto;
    }

    @Override
    public AppointmentRequestDto findByEmail(String byPatientEmail) {
        Optional<Appointment> optionalAppointment = Optional.ofNullable(this.iAppointmentRepository.findByPatientEmail(byPatientEmail));
        Appointment appointment = optionalAppointment.get();

        AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto();
        BeanUtils.copyProperties(appointment, appointmentRequestDto);

        return appointmentRequestDto;
    }

}
