package com.doctor.services;

import com.doctor.requestdto.AppointmentRequestDto;
import java.io.FileNotFoundException;
import java.util.List;


public interface AppointmentService {
    AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto);

    List<AppointmentRequestDto> findByPatientEmail(String patientEmail) throws Exception;

    AppointmentRequestDto getAppointment(Long appointmentId) throws FileNotFoundException;
}
