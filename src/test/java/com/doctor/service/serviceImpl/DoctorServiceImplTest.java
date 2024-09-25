package com.doctor.service.serviceImpl;

import com.doctor.dto.DoctorRequestDto;
import com.doctor.entities.Doctor;
import com.doctor.exception.ControllerException;
import com.doctor.exception.DoctorNotFoundException;
import com.doctor.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {
    @Mock
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private DoctorServicesImpl doctorServices;

    @Mock
    private DoctorRepository doctorRepository;

    private Doctor doctor;

    @DisplayName("Unit test case for the add doctor")
    @Test
    void testAddDoctorSuccess() {
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorId(1L);
        doctorRequestDto.setDoctorEmail("test@example.com");
        doctorRequestDto.setDoctorGender("female");
        doctorRequestDto.setDoctorMobileNumber("1233");
        doctorRequestDto.setDoctorName("payal");
        doctorRequestDto.setDoctorPassword("password");
        doctorRequestDto.setDoctorSpecialization("doctor");
        Doctor doctor = new Doctor();
        doctor.setDoctorId(1L);
        doctor.setRole("Demo");
        doctor.setDoctorEmail("test@example.com");
        doctor.setDoctorGender("female");
        doctor.setDoctorMobileNumber("1233");
        doctor.setDoctorName("payal");
        doctor.setDoctorPassword("password");
        doctor.setDoctorSpecialization("doctor");

        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        Doctor result = doctorServices.addDoctor(doctorRequestDto);
        assertEquals(doctor.getDoctorEmail(), result.getDoctorEmail());
        assertEquals(doctor.getDoctorGender(),result.getDoctorGender());
        assertEquals(doctor.getDoctorName(),result.getDoctorName());
    }


    @DisplayName("Unit test case for the update doctor")
    @Test
    void testUpdateSuccess() throws DoctorNotFoundException {
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
        doctorRequestDto.setDoctorPassword("hello");
        doctorRequestDto.setDoctorMobileNumber("1234");
        doctorRequestDto.setDoctorEmail("payal@mail");
        doctorRequestDto.setDoctorName("payal");

        Doctor doctor = new Doctor();
        doctor.setDoctorPassword("hello");
        doctor.setDoctorMobileNumber("1234");
        doctor.setDoctorEmail("payal@mail");
        doctor.setDoctorName("payal");

        when(doctorRepository.findByDoctorEmail(doctorRequestDto.getDoctorEmail())).thenReturn(doctor);
        doctorServices.updateDoctor(doctorRequestDto.getDoctorEmail(), doctorRequestDto);

    }
    @DisplayName("Unit test case for the getDoctorById")
    @Test
    public void getDoctorByIdSuccess(){
        Doctor doctor = new Doctor();
        doctor.setDoctorId(1L);
//        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        given(doctorRepository.findById(1L)).willReturn(Optional.of(doctor));
        Doctor savedDoctor = doctorServices.getDoctorById(doctor.getDoctorId()).get();
        assertThat(savedDoctor).isNotNull();

    }
    @DisplayName("Unit test case for the doctor by id")
    @Test
    void testGetDoctorByIdValidId() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorId);
        Optional<Doctor> optionalDoctor = Optional.of(doctor);
        when(doctorRepository.findById(doctorId)).thenReturn(optionalDoctor);
        Optional<Doctor> result = doctorServices.getDoctorById(doctorId);
        assertEquals(optionalDoctor, result);
    }


    @DisplayName("Unit test for getAllDoctors method")
    @Test
    public void givenDoctorsList_whenGetAllDoctors_thenReturnDoctorsList() {
        Doctor doctor1 = Doctor.builder()
                .doctorGender("male")
                .doctorName("Ram")
                .doctorSpecialization("Cardiologist")
                .doctorEmail("ram@gmail.com")
                .doctorPassword("ram123")
                .doctorMobileNumber("1234567890")
                .build();

        Doctor doctor2 = Doctor.builder()
                .doctorGender("female")
                .doctorName("Sita")
                .doctorSpecialization("Neurologist")
                .doctorEmail("sita@gmail.com")
                .doctorPassword("sita123")
                .doctorMobileNumber("0987654321")
                .build();

        given(doctorRepository.findAll()).willReturn(List.of(doctor1, doctor2));

        List<Doctor> doctorList = doctorServices.getAllDoctors();
        assertThat(doctorList).isNotNull();
        assertThat(doctorList.size()).isEqualTo(2);
        assertThat(doctorList.get(0).getDoctorName()).isEqualTo("Ram");
    }

    @DisplayName("Unit test case for the deleteDoctorById_whenDoctorExists_shouldDeleteDoctor")
    @Test
    public void deleteDoctorById() {
        Long doctorId = 1L;
        given(doctorRepository.existsById(doctorId)).willReturn(true);
        willDoNothing().given(doctorRepository).deleteById(doctorId);
        doctorServices.deleteDoctorById(doctorId);
        verify(doctorRepository, times(1)).existsById(doctorId);
        verify(doctorRepository, times(1)).deleteById(doctorId);
    }

    @DisplayName("Unit test case for the deleteDoctorById_whenDoctorDoesNotExist_shouldThrowException")
    @Test
    public void deleteDoctorByIds() {
        Long doctorId = 1L;
        given(doctorRepository.existsById(doctorId)).willReturn(false);

        assertThrows(ControllerException.class, () -> doctorServices.deleteDoctorById(doctorId));

        verify(doctorRepository, times(1)).existsById(doctorId);
        verify(doctorRepository, never()).deleteById(doctorId);
    }

    @Test
    void testGetDoctorByIdInvalidId() {
        Long doctorId = -1L;
        ControllerException exception = assertThrows(ControllerException.class, () -> doctorServices.getDoctorById(doctorId));
        assertEquals("Invalid id", exception.getMessage());
    }

    @Test
    void testAddDoctorNullRequest() {
        assertThrows(NullPointerException.class, () -> doctorServices.addDoctor(null));
    }

}

