package com.doctor.serviceImpl;

import com.doctor.entities.Appointment;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repositories.IAppointmentRepository;
import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.requestDto.DoctorRequestDto;
import com.doctor.services.IAppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();

            AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto();
            BeanUtils.copyProperties(appointment, appointmentRequestDto);

            return appointmentRequestDto;
        } else
            throw new ControllerException("NO DATA FOUND WITH GIVEN EMAIL");
    }

    @Override
    public AppointmentRequestDto updateAppointment(Long appointmentId, AppointmentRequestDto appointmentRequestDto) throws BusinessException {
        Appointment appointment = this.iAppointmentRepository.findById(appointmentId).orElseThrow(
                () -> new BusinessException("NO DATA FOUND WITH GIVEN ID")
        );

        BeanUtils.copyProperties(appointmentRequestDto, appointment);

        Appointment appointment1 = iAppointmentRepository.save(appointment);

        BeanUtils.copyProperties(appointment1, appointmentRequestDto);

        return appointmentRequestDto;

    }

    @Override
    public String deleteAppointment(Long appointmentId) throws BusinessException {
        Appointment appointment = this.iAppointmentRepository.findById(appointmentId).orElseThrow(
                () -> new BusinessException("NO DATA FOUND WITH GIVEN ID")
        );
        this.iAppointmentRepository.delete(appointment);
        return "Appointment deleted successfully";
    }

    @Override
    public AppointmentRequestDto getAppointment(Long appointmentId) throws BusinessException {
        Appointment appointment = this.iAppointmentRepository.findById(appointmentId).orElseThrow(
                () -> new BusinessException("NO DATA FOUND WITH GIVEN ID")
        );

        AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto();

        BeanUtils.copyProperties(appointment, appointmentRequestDto);

        return appointmentRequestDto;
    }

    @Override
    public List<AppointmentRequestDto> getAllAppointments() throws BusinessException {
       List<Appointment> appointments = this.iAppointmentRepository.findAll();
     List<AppointmentRequestDto> appointmentRequestDtos = appointments.stream()
             .map((appointments1 -> modelMapper.map(appointments1, AppointmentRequestDto.class))).toList();

     if(appointmentRequestDtos.isEmpty())
         throw new BusinessException("NO APPOINTMENT FOUND");
     else
         return appointmentRequestDtos;

    }
}
