package com.doctor.services;

import com.doctor.entities.Appointment;
import com.doctor.exception.BusinessException;
import com.doctor.requestDto.AppointmentRequestDto;

import java.util.List;

public interface IAppointmentService {

    AppointmentRequestDto saveAppointment(AppointmentRequestDto appointmentRequestDto);

    List<AppointmentRequestDto> findByEmail(String byPatientEmail);

    AppointmentRequestDto updateAppointment(Long appointmentId, AppointmentRequestDto appointmentRequestDto) throws BusinessException;

    String deleteAppointment(Long appointmentId) throws BusinessException;

    AppointmentRequestDto getAppointment(Long appointmentId) throws BusinessException;

    List<AppointmentRequestDto> getAllAppointments() throws BusinessException;

}
