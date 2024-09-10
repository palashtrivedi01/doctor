package com.doctor.service;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.dto.AppointmentResponceDto;
import com.doctor.entities.Appointment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface AppointmentService {


    public Appointment findByEmail(String patientEmail);
//    public Appointment saveAppointment(Appointment appointment);
    AppointmentResponceDto saveAppoinment(AppointmentRequestDto appointmentRequestDto, MultipartFile file) throws IOException;

}
