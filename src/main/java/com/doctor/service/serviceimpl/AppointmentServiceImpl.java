package com.doctor.service;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.entities.Appointment;
import com.doctor.exception.ControllerException;
import com.doctor.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentServiceInterface {
    @Autowired
    private AppointmentRepository appointmentRepository;


//    @Override
//    public ResponseEntity<Appointment> createAppointment(Appointment appointment) {
//
//        Appointment appointment1 = this.appointmentRepository.save(appointment);
//
//        if(appointment1 != null) {
//            return ResponseEntity.ok(appointment1);
//        }else
//            throw new ControllerException("Data for table is mendatory for creating appointment");
//    }


    @Override
    public AppointmentRequestDto findByPatientEmail(String email) {

        Appointment appointment = this.appointmentRepository.findByPatientEmail(email);
        if (email != null) {
            AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto();
            appointmentRequestDto.setAppointmentDate(appointment.getAppointmentDate());
            appointmentRequestDto.setDoctorEmail(appointment.getDoctorEmail());
            appointmentRequestDto.setDoctorName(appointment.getDoctorName());
            appointmentRequestDto.setPatientEmail(appointment.getPatientEmail());
            appointmentRequestDto.setPatientName(appointment.getPatientName());
            appointmentRequestDto.setPatientMobileNumber(appointment.getPatientMobileNumber());
            appointmentRequestDto.setData(appointment.getData());
            appointmentRequestDto.setFileAttech(appointment.getFileAttech());
//            return ResponseEntity.ok(appointmentRequestDto);
            return appointmentRequestDto;

        } else {
            throw new ControllerException("Not worked");
        }
    }


    @Override
    public AppointmentRequestDto addAppointment(AppointmentRequestDto appointmentRequestDto) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
        appointment.setDoctorEmail(appointmentRequestDto.getDoctorEmail());
        appointment.setDoctorName(appointmentRequestDto.getDoctorName());
        appointment.setPatientEmail(appointmentRequestDto.getPatientEmail());
        appointment.setPatientName(appointmentRequestDto.getPatientName());
        appointment.setPatientMobileNumber(appointmentRequestDto.getPatientMobileNumber());
        appointment.setData(appointmentRequestDto.getData());
        appointment.setFileAttech(appointmentRequestDto.getFileAttech());
        appointmentRepository.save(appointment);


        return appointmentRequestDto;
    }

}


