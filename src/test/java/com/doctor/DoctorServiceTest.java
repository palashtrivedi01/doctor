package com.doctor;

import com.doctor.dto.DoctorRequestDTO;
import com.doctor.entities.Doctor;
import com.doctor.repository.DoctorRepository;
import com.doctor.service.serviceimpl.HelloDoctorServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @InjectMocks
    private HelloDoctorServices helloDoctorServices;


    @Mock
    private DoctorRepository doctorRepository;


    @Test
    public void testAddDoctor() {
        DoctorRequestDTO doctorRequestDTO = new DoctorRequestDTO();
        doctorRequestDTO.setHospitalName("Bombay Hospital");


        Doctor doctor = new Doctor();
        doctor.setDoctorGender(doctorRequestDTO.getDoctorGender());
        doctor.setDoctorName(doctorRequestDTO.getDoctorName());
        doctor.setHospitalName(doctorRequestDTO.getHospitalName());
        doctor.setDoctorEmail(doctorRequestDTO.getDoctorEmail());


        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);


        DoctorRequestDTO result = helloDoctorServices.addDoctor(doctorRequestDTO);

        assertNotNull(result);
        assertEquals("Bombay Hospital", result.getHospitalName());
       // assertEquals("imza@gmail.com", result.getDoctorEmail());
       // verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    public void testFindByDoctorEmail() {

        String email = "azmi@gmail.com";
        Doctor doctor = new Doctor();
        doctor.setDoctorEmail(email);

        when(doctorRepository.findByDoctorEmail(email)).thenReturn(doctor);

        Doctor doctor1 = helloDoctorServices.findByDoctorEmail(email);


        assertNotNull(doctor);
        assertEquals(doctor.getDoctorEmail(), doctor1.getDoctorEmail());
        verify(doctorRepository, times(1)).findByDoctorEmail(email);
    }

    @Test
    public void testFindDoctorById() {

        Long id = 1L;
        Doctor doctor = new Doctor();
        doctor.setDoctorId(id);

        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
        Optional<Doctor> doctor1 = helloDoctorServices.findDoctorById(id);

        assertNotNull(doctor1);
        assertEquals(doctor.getDoctorId(), doctor1.get().getDoctorId());
        verify(doctorRepository, times(1)).findById(id);

    }

    @Test
    public void testFindAllDoctor() {
        List<Doctor> doctors = Arrays.asList(new Doctor(), new Doctor());
        when(doctorRepository.findAll()).thenReturn(doctors);

        List<Doctor> doctors1 = helloDoctorServices.findAllDoctor();

        assertNotNull(doctors1);
        verify(doctorRepository, times(1)).findAll();
        DoctorRequestDTO doctorRequestDTO = new DoctorRequestDTO();
        doctorRequestDTO.setDoctorGender("female");
        doctorRequestDTO.setDoctorName("Imza");
        doctorRequestDTO.setDoctorEmail("imza@gmail.com");

    }

    @Test
    public void testDeleteById()
    {

        Long id = 1L;
        Doctor doctor = new Doctor();
        doctor.setDoctorId(id);
        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

        String doctor1=helloDoctorServices.deleteByDoctorId(id);
        verify(doctorRepository, times(1)).findById(id);


    }
}










