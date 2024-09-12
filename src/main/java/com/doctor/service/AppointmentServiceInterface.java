package com.doctor.service;

import com.doctor.entities.Appointment;
import org.springframework.http.ResponseEntity;

public interface AppointmentServiceInterface {

    public ResponseEntity<Appointment> createAppointment(Appointment appointment);

    public ResponseEntity<Appointment> findByPatientEmail(String email);






   // Appointment store(MultipartFile file);
  //  public void init();


}
