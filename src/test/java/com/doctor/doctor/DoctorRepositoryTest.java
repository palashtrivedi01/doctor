package com.doctor.doctor;

import com.doctor.entities.Doctor;
import com.doctor.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
   public  class DoctorRepositoryTest {
    @InjectMocks
    private DoctorRepository doctorRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testFindByDoctorEmail() {
        String doctorEmail = "azmi@gmail.com";
        Doctor doctor1 = new Doctor();
        doctor1.setDoctorEmail(doctorEmail);
        doctor1.setDoctorName("azmi");

        when(doctorRepository.findByDoctorEmail(doctorEmail)).thenReturn(doctor1);

        Doctor doctor2 = doctorRepository.findByDoctorEmail(doctorEmail);


        assertNotNull(doctor2);
        assertEquals(doctorEmail, doctor2.getDoctorEmail());
//        assertEquals("azmi", doctor2.getDoctorName());

        verify(doctorRepository, times(1)).findByDoctorEmail(doctorEmail);

    }

}