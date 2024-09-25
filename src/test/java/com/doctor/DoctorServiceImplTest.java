package com.doctor;

import com.doctor.controller.DoctorController;
import com.doctor.entity.Doctor;
import com.doctor.repository.DoctorRepo;
import com.doctor.requestdto.DoctorRequestDto;
import com.doctor.serviceimpl.DoctorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DoctorServiceImplTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorRepo doctorRepo;

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorServiceImpl).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void addDoctorTest() throws Exception {
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorName("John Doe");
        doctorRequestDto.setDoctorPassword("password123");
        doctorRequestDto.setDoctorGender("Male");
        doctorRequestDto.setDoctorMobileNumber(1234567890L);
        doctorRequestDto.setDoctorEmail("john.doe@example.com");
        doctorRequestDto.setDoctorSpecialization("Cardiology");
        doctorRequestDto.setHospitalName("General Hospital");

        Doctor doctor = new Doctor();
        doctor.setDoctorName("John Doe");
        doctor.setDoctorPassword("password123");
        doctor.setDoctorGender("Male");
        doctor.setDoctorMobileNumber(1234567890L);
        doctor.setDoctorEmail("john.doe@example.com");
        doctor.setDoctorSpecialization("Cardiology");
        doctor.setHospitalName("General Hospital");

        when(doctorRepo.save(any(Doctor.class))).thenReturn(doctor);
        DoctorRequestDto result = doctorServiceImpl.addDoctor(doctorRequestDto);
        assertEquals(doctor.getDoctorEmail(), result.getDoctorEmail());
        assertEquals(doctor.getDoctorPassword(), result.getDoctorPassword());
        assertEquals(doctor.getDoctorName(), result.getDoctorName());
        assertEquals(doctor.getHospitalName(), result.getHospitalName());
        assertEquals(doctor.getDoctorMobileNumber(), result.getDoctorMobileNumber());
        assertEquals(doctor.getDoctorGender(), result.getDoctorGender());
        assertEquals(doctor.getDoctorSpecialization(), result.getDoctorSpecialization());
    }

    @Test
    public void getDoctorByDoctorEmailTest() throws Exception {
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorName("John Doe");
        doctorRequestDto.setDoctorPassword("password123");
        doctorRequestDto.setDoctorGender("Male");
        doctorRequestDto.setDoctorMobileNumber(1234567890L);
        doctorRequestDto.setDoctorEmail("john.doe@example.com");

        Doctor doctor=new Doctor();
        doctor.setDoctorName("John Doe");
        doctor.setDoctorPassword("password123");
        doctor.setDoctorGender("Male");
        doctor.setDoctorMobileNumber(1234567890L);
        doctor.setDoctorEmail("john.doe@example.com");
        when(doctorRepo.findByDoctorEmail(doctor.getDoctorEmail())).thenReturn(doctor);
        assertEquals(doctorRequestDto.getDoctorEmail(),doctor.getDoctorEmail());

    }

    @Test
    public void getDoctorByDoctorIdTest() throws Exception {
        Doctor doctor=new Doctor();
        doctor.setDoctorName("John Doe");
        doctor.setDoctorPassword("password123");
        doctor.setDoctorGender("Male");
        doctor.setDoctorMobileNumber(1234567890L);
        doctor.setDoctorId(2L);

        DoctorRequestDto doctorRequestDto=new DoctorRequestDto();
        doctorRequestDto.setDoctorName("John Doe");
        doctorRequestDto.setDoctorPassword("password123");
        doctorRequestDto.setDoctorGender("Male");
        doctorRequestDto.setDoctorMobileNumber(1234567890L);
        doctorRequestDto.setDoctorId(2L);
        when(doctorRepo.findById(doctor.getDoctorId())).thenReturn(Optional.of(doctor));
        assertEquals(doctorRequestDto.getDoctorId(),doctor.getDoctorId());
    }





}
