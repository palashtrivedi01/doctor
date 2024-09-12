package com.doctor.serviceimpl;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repository.AppointmentRepo;
import com.doctor.repository.DoctorRepo;
import com.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;


    @Override
    public Doctor addDoctor(Doctor doctor) {
        Doctor doctor1 = doctorRepo.save(doctor);
        return doctor1;
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, String doctorEmail) throws BusinessException {
        Optional<Doctor> byDoctorEmail = doctorRepo.findByDoctorEmail(doctorEmail);
        if (byDoctorEmail.isPresent()) {
            Doctor doctor1 = byDoctorEmail.get();
            doctor1.setDoctorName(doctor.getDoctorName());
            doctor1.setDoctorGender(doctor.getDoctorGender());
            doctor1.setDoctorMobileNumber(doctor.getDoctorMobileNumber());
            doctor1.setDoctorPassword(doctor.getDoctorPassword());
            doctor1.setDoctorSpecialization(doctor.getDoctorSpecialization());
            doctor1.setDoctorEmail(doctorEmail);
            doctorRepo.save(doctor1);
            return doctor1;
        }
        else {
            throw new BusinessException("Doctor not found with given Doctor Email : "+ doctorEmail);
        }
    }

    @Override
    public Doctor getDoctorByDoctorEmail(String doctorEmail) throws  ControllerException {
        Optional<Doctor> byDoctorEmail = doctorRepo.findByDoctorEmail(doctorEmail);
        if (byDoctorEmail.isPresent()) {
            return byDoctorEmail.get();
        }
        throw new ControllerException("Doctor not found with given Doctor Email : "+ doctorEmail);
    }

    @Override
    public Doctor getDoctorByDoctorId(Long doctorId) throws ControllerException {
        if(doctorRepo.existsById(doctorId)) {
            return doctorRepo.findById(doctorId).get();
        }else {
            throw new ControllerException("Doctor not found with given Doctor Id: "+ doctorId);
        }

    }

    @Override
    public List<Doctor> getAllDoctors() throws ControllerException {
        List<Doctor> all = doctorRepo.findAll();
        if(all.isEmpty()) {
            throw  new ControllerException("No Doctor available. ");
        }
        return all;
    }

    @Override
    public String deleteDoctor(Long doctorId) throws ControllerException {
        if (!doctorRepo.existsById(doctorId)) {
            throw new ControllerException("Doctor not found Exception with  given Doctor Id : "+ doctorId);
        }
        doctorRepo.deleteById(doctorId);
        return "Doctor deleted successfully";

    }

    @Override
    public List<Appointment> getAppointmentByDoctorEmail(String doctorEmail) throws BusinessException {
        Optional<Doctor> doctor = doctorRepo.findByDoctorEmail(doctorEmail);
        Optional<List<Appointment>> appointment = appointmentRepo.findByDoctorEmail(doctorEmail);


        if (doctor.isPresent() && appointment.isPresent()) {
            return appointment.get();
        }
        else {
            throw new BusinessException("Doctor not found Exception with given email : "+ doctorEmail);

        }
    }



}
