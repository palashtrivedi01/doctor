package com.doctor.doctor;


import com.doctor.controller.DoctorController;
import com.doctor.dto.DoctorRequestDTO;
import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.service.serviceimpl.HelloDoctorServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
;




import static org.junit.jupiter.api.Assertions.*;




public class DoctorControllerTest {


    @Mock
    private HelloDoctorServices doctorService;


    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDoctor_Success() {

        DoctorRequestDTO doctorRequestDTO = new DoctorRequestDTO();
        doctorRequestDTO.setDoctorName("Azmi Khan");

        DoctorRequestDTO savedDoctor = new DoctorRequestDTO();
        savedDoctor.setDoctorName("Azmi Khan");

        when(doctorService.addDoctor(doctorRequestDTO)).thenReturn(savedDoctor);


        ResponseEntity<DoctorRequestDTO> response = doctorController.addDoctor(doctorRequestDTO);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedDoctor, response.getBody());
        verify(doctorService).addDoctor(doctorRequestDTO);

    }


    @Test
    void testUpdateDoctor_SuccessTest() {

        String doctorEmail = "azmmikhan@gmail.com";
        Doctor doctorToUpdate = new Doctor();
        doctorToUpdate.setDoctorName("azmi");

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setDoctorName("azmi updated");

        when(doctorService.updateDoctor(doctorEmail, doctorToUpdate)).thenReturn(updatedDoctor);


        ResponseEntity<Doctor> response = doctorController.updateDoctor(doctorEmail, doctorToUpdate);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDoctor, response.getBody());
        verify(doctorService).updateDoctor(doctorEmail, doctorToUpdate);
    }


    @Test
    void testGetDoctorById_Success() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setDoctorName("imza");

        Optional<Doctor> doctor1 = Optional.of(doctor);
        when(doctorService.findDoctorById(doctorId)).thenReturn(doctor1);


        ResponseEntity<Optional<Doctor>> response = doctorController.getDoctorById(doctorId);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctor1, response.getBody());
        verify(doctorService).findDoctorById(doctorId);

    }


    @Test
    void testGetDoctorByEmailTest() {

        String doctorEmail = "imza@email.com";
        when(doctorService.findByDoctorEmail(doctorEmail)).thenReturn(null);


        ResponseEntity<Doctor> response = doctorController.getDoctorByEmail(doctorEmail);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
        verify(doctorService).findByDoctorEmail(doctorEmail);
    }


    @Test
    public void testGetAppointmentsByDoctorEmail() {

        String doctorEmail = "azmi@email.com";
        List<Appointment> appointments = Arrays.asList(
                new Appointment(),
                new Appointment()
        );


        when(doctorService.getAppointmentsByDoctorEmail(doctorEmail)).thenReturn(appointments);


        ResponseEntity<List<Appointment>> response = doctorController.getAppointmentsByDoctorEmail(doctorEmail);


        assertNotNull(response);

        assertEquals(appointments, response.getBody());

        verify(doctorService, times(1)).getAppointmentsByDoctorEmail(doctorEmail);
    }


    @Test
    public void deleteDoctor_Success() {
        Long id = 2L;


        Doctor doctor = new Doctor();
        doctor.setDoctorId(id);
//      when(doctorService.deleteByDoctorId(id).thenReturn(doctor));
        when(doctorService.deleteByDoctorId(2L)).thenReturn(null);

        ResponseEntity<String> response = doctorController.deleteDoctor(id);

    }}

