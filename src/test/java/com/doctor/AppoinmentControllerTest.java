package com.doctor;

import com.doctor.entities.Appointment;
import com.doctor.repository.AppointmentRepository;
import com.doctor.service.serviceImpl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AppoinmentControllerTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @InjectMocks
    private AppointmentServiceImpl appointmentService;


    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    public void testFindByPatientEmail_Success() {

        String patientEmail = "payal@example.com";

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate("1-09-24");
        appointment.setDoctorEmail("Azmi@gmail.com");
        appointment.setDoctorName("Azmi khan");
        appointment.setPatientEmail(patientEmail);
        appointment.setPatientName("payal");


        when(appointmentRepository.findByPatientEmail(patientEmail)).thenReturn(Optional.of(appointment));
        System.out.println(appointmentService.findByEmail(patientEmail));

        appointment.getAppointmentDate();

        //verify(appointmentRepository, times(1)).findByPatientEmail(patientEmail);
    }

}

