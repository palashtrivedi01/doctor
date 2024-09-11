package com.doctor.service.serviceImpl;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.dto.AppointmentResponceDto;
import com.doctor.entities.Appointment;
import com.doctor.repository.AppointmentRepository;
import com.doctor.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Override
    public Appointment findByEmail(String patientEmail) {
        Optional<Appointment> byPatientEmail = appointmentRepository.findByPatientEmail(patientEmail);
        return byPatientEmail.orElse(null);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        Appointment save = this.appointmentRepository.save(appointment);
        return save;
    }
}