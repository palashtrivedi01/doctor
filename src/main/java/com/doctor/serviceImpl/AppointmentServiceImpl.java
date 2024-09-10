package com.doctor.serviceImpl;

import com.doctor.entities.Appointment;
import com.doctor.repositories.IAppointmentRepository;
import com.doctor.services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {

       Long doctorId = appointment.getDoctors().getDoctorId();
       Long patientId = appointment.getPatients().getPatientId();

       Appointment savedAppointment = iAppointmentRepository.save(appointment);
       return savedAppointment;
       }

    @Override
    public Appointment findByEmail(String byPatientEmail) {
        Optional<Appointment> optionalAppointment = Optional.ofNullable(this.iAppointmentRepository.findByPatientEmail(byPatientEmail));
        return optionalAppointment.orElse(null);
    }


}
