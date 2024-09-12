package com.doctor.service;

import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.exception.ControllerException;
import com.doctor.repository.AppointmentRepository;
import com.doctor.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelloDoctorServices implements DoctorServiceInterface{

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    //public Doctor doctorRequestDTO(Doctor doctor) {
       // if (doctorRepository.existsById(doctor.getDoctorId())) {
         //   throw new ControllerException("Doctor already exists");
        //} else {
         //   return this.doctorRepository.save(doctor);

       // }
  //  }


    @Override
    public Doctor addDoctor(Doctor doctor) {
        if (doctorRepository.existsById(doctor.getDoctorId())) {
            throw new ControllerException("Doctor already exists");
        } else {
            return this.doctorRepository.save(doctor);
        }
    }
    @Override
    public Doctor updateDoctor(String doctorEmail, Doctor doctor) {
       Doctor exisistingDoctor=doctorRepository.findByDoctorEmail(doctorEmail);
       exisistingDoctor.setDoctorName(doctor.getDoctorName());
       exisistingDoctor.setDoctorEmail(doctor.getDoctorEmail());
        System.out.println(exisistingDoctor.getDoctorEmail());

       this.doctorRepository.save(exisistingDoctor);

        if(doctorEmail==null|| doctorEmail.isEmpty())
        {
            throw new ControllerException("Wrong entry");

        }

        return exisistingDoctor;
    }

    @Override
    public Optional<Doctor> findDoctorById(Long doctorId) {
      Optional<Doctor> doctor=this.doctorRepository.findById(doctorId);

      if (doctor.isEmpty())
      {
          throw new ControllerException("Id not found");
      }

      return doctor;
    }

@Override
    public Doctor findByDoctorEmail(String doctorEmail)

     {
        Doctor doctor=this.doctorRepository.findByDoctorEmail(doctorEmail);

         if(doctorEmail==null|| doctorEmail.isEmpty())
         {
             throw new ControllerException("Wrong entry");

         }
         else
             return doctor;

     }

    @Override
    public List<Doctor> findAllDoctor() {
        List<Doctor> doctors=this.doctorRepository.findAll();

        if(doctors.isEmpty())
        {
            throw  new ControllerException("No doctor found");
        }

        return doctors;
    }

    @Override
    public String deleteByDoctorId(Long doctorId) {
        if (doctorId == null) {
            throw new ControllerException("Id not found");
        } else {
            Doctor doctor;
            this.doctorRepository.deleteById(doctorId);
        }
        return "deleted";
    }

    @Override
    public List<Appointment> getAppointmentsByDoctorEmail(String doctorEmail) {
        List<Appointment> appointment = this.appointmentRepository.findByDoctorEmail(doctorEmail);
        return appointment;
    }



}



