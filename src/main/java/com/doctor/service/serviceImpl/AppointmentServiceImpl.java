package com.doctor.service.serviceImpl;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.dto.AppointmentResponceDto;
import com.doctor.entities.Appointment;
import com.doctor.exception.BusinessException;
import com.doctor.repository.AppointmentRepository;
import com.doctor.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Value("${upload.directory}")
//    private String uploadDirectory;

    @Override
    public Appointment findByEmail(String patientEmail) {
        Optional<Appointment> byPatientEmail = appointmentRepository.findByPatientEmail(patientEmail);
        return byPatientEmail.orElse(null);
    }

    @Override
    public AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto) {
       Appointment appointment1 =  new Appointment();
        appointment1.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
//        appointment1.setAppointmentId(appointmentRequestDto.getAppointmentId());
        appointment1.setDoctorEmail(appointmentRequestDto.getDoctorEmail());
        appointment1.setTime(appointmentRequestDto.getTime());
        appointment1.setDoctorName(appointmentRequestDto.getDoctorName());
        appointment1.setFileAttech(appointmentRequestDto.getFileAttech());

        Appointment save = appointmentRepository.save(appointment1);
        return appointmentRequestDto;

    }
}