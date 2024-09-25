package com.doctor.service;

import com.doctor.entities.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getAllPatient() ;

    Patient addPatient(Patient patient) ;

    Patient updatePatient(Patient patient);
}
