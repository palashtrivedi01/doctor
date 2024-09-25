package com.doctor.serviceimpl;

import com.doctor.entity.Appointment;

import com.doctor.exception.InvalidInputException;
import com.doctor.repository.AppointmentRepo;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto) {
        System.out.println(appointmentRequestDto);
        Appointment a = new Appointment();
        a.setDoctorName(appointmentRequestDto.getDoctorName());
        a.setFileAttach(appointmentRequestDto.getFileAttach());
        a.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
        a.setDoctorEmail(appointmentRequestDto.getDoctorEmail());
        a.setPatientName(appointmentRequestDto.getPatientName());
        a.setPatientEmail(appointmentRequestDto.getPatientEmail());
        a.setTime(appointmentRequestDto.getTime());
        appointmentRepo.save(a);
        return appointmentRequestDto;
    }

    @Override
    public List<AppointmentRequestDto> findByPatientEmail(String patientEmail) throws InvalidInputException {
        List<Appointment> optionalAppointment = appointmentRepo.findByPatientEmail(patientEmail);
        if (optionalAppointment == null || optionalAppointment.isEmpty()) {
            throw new InvalidInputException("Invalid input Exception");
        }


        List<AppointmentRequestDto> list = optionalAppointment.stream().map(appointment -> {
            AppointmentRequestDto dto = new AppointmentRequestDto();
            dto.setDoctorName(appointment.getDoctorName());
            dto.setFileAttach(appointment.getFileAttach());
            dto.setAppointmentDate(appointment.getAppointmentDate());
            dto.setDoctorEmail(appointment.getDoctorEmail());
            dto.setPatientName(appointment.getPatientName());
            dto.setPatientEmail(appointment.getPatientEmail());
            dto.setTime(appointment.getTime());
            return dto;
        }).toList();
        return list;
    }

    @Override
    public AppointmentRequestDto getAppointment(Long appointmentId) throws FileNotFoundException {
        Optional<Appointment> byId = appointmentRepo.findById(appointmentId);
        if (byId.isPresent()) {
            AppointmentRequestDto dto= new AppointmentRequestDto();
            dto.setAppointmentId(appointmentId);
            dto.setFileAttach(byId.get().getFileAttach());
            dto.setDoctorName(byId.get().getDoctorName());
            dto.setPatientName(byId.get().getPatientName());
            dto.setPatientEmail(byId.get().getPatientEmail());
            dto.setTime(byId.get().getTime());
            return dto;

        }
        throw new FileNotFoundException("file not found with given id: " + appointmentId);
    }

}