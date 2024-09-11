package com.doctor.serviceimpl;

import com.doctor.entity.Appointment;
import com.doctor.exception.EmptyInputException;
import com.doctor.exception.InvalidInputException;
import com.doctor.repository.AppointmentRepo;
import com.doctor.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;



    @Override
    public Appointment saveAppointment(Appointment appointment) {

        Appointment appointment1 = appointmentRepo.save(appointment);
        return appointment1;

    }

    @Override
    public Appointment findByPatientEmail(String patientEmail) throws InvalidInputException, EmptyInputException {
        Optional<Appointment> optionalAppointment = appointmentRepo.findByPatientEmail(patientEmail);
        if (optionalAppointment.isPresent()) {
            return optionalAppointment.get();
        } else if (!optionalAppointment.equals(patientEmail)) {
            throw new InvalidInputException("invalid input Exception");
        }
        else {
            throw new EmptyInputException("Input is Empty");
        }




    }

    @Override
    public Appointment findAppointmentByDoctorEmail(String doctorEmail) throws Exception {
        Optional<List<Appointment>> byDoctorEmail = appointmentRepo.findByDoctorEmail(doctorEmail);
        if (byDoctorEmail.isPresent()) {
            return (Appointment) byDoctorEmail.get();
        }
        else {
            throw new EmptyInputException("Input is Empty");
        }



    }


}