package com.doctor.appointment;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.entities.Appointment;
import com.doctor.repository.AppointmentRepository;
import com.doctor.service.serviceimpl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    @InjectMocks
    private AppointmentServiceImpl appointmentService;



    @Mock
    private AppointmentRepository appointmentRepository;

    @Test
    public void testFindByPatientEmail() {

        String patientEmail = "payal@example.com";


        Appointment appointment = new Appointment();
        appointment.setAppointmentDate("1-09-24");
        appointment.setDoctorEmail("Azmi@gmail.com");
        appointment.setDoctorName("Azmi khan");
        appointment.setPatientEmail(patientEmail);
        appointment.setPatientName("payal");
        appointment.setPatientMobileNumber(1234567321L);


        when(appointmentRepository.findByPatientEmail(patientEmail)).thenReturn(appointment);
//    System.out.println(appointmentService.findByPatientEmail(patientEmail));

        AppointmentRequestDto result = appointmentService.findByPatientEmail(patientEmail);

//
             assertNotNull(result);
             assertEquals(appointment.getPatientEmail(), result.getPatientEmail());
        assertEquals("1-09-24", result.getAppointmentDate());
        assertEquals("Azmi@gmail.com", result.getDoctorEmail());
        assertEquals("Azmi khan", result.getDoctorName());
        assertEquals(patientEmail, result.getPatientEmail());
        assertEquals("payal", result.getPatientName());
        assertEquals(1234567321L, result.getPatientMobileNumber());


      verify(appointmentRepository, times(1)).findByPatientEmail(patientEmail);

    }

    @Test
    public  void testAddDoctor()
    {
    AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto();
    appointmentRequestDto.setDoctorEmail("payal@example.com");
    appointmentRequestDto.setDoctorName("Azmi khan");
    appointmentRequestDto.setPatientEmail("payal@example.com");


Appointment appointment = new Appointment();
appointment.setDoctorEmail(appointmentRequestDto.getDoctorEmail());
appointment.setDoctorName(appointmentRequestDto.getDoctorName());
appointment.setPatientEmail(appointmentRequestDto.getPatientEmail());

when(appointmentRepository.save(appointment)).thenReturn(appointment);

AppointmentRequestDto d =appointmentService.addAppointment(appointmentRequestDto);
    }


}







