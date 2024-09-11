package com.doctor.serviceImpl;

import com.doctor.entities.Appointment;
import com.doctor.entities.Doctor;
import com.doctor.exception.ControllerException;
import com.doctor.repositories.IAppointmentRepository;
import com.doctor.repositories.IDoctorRepository;
import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.requestDto.DoctorRequestDto;
import com.doctor.services.IDoctorService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private IDoctorRepository iDoctorRepository;

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private DoctorRequestDto entityToDto(Doctor savedDoctor) {
        return modelMapper.map(savedDoctor, DoctorRequestDto.class);
    }

    private Doctor dtoToEntity(DoctorRequestDto doctorRequestDto) {
        return modelMapper.map(doctorRequestDto, Doctor.class);
    }


    @Override
    public DoctorRequestDto saveDoctor(DoctorRequestDto doctorRequestDto) {

        Doctor doctor = dtoToEntity(doctorRequestDto);
        Doctor savedDoctor = iDoctorRepository.save(doctor);

        DoctorRequestDto newDoctorRequestDto = entityToDto(savedDoctor);

        return newDoctorRequestDto;
    }

    @Override
    public DoctorRequestDto updateDoctor(String doctorEmail, DoctorRequestDto doctorRequestDto) {
        Doctor savedDoctor = this.iDoctorRepository.findByDoctorEmail(doctorEmail);
        if(savedDoctor != null) {
            savedDoctor.setDoctorName(doctorRequestDto.getDoctorName());
            savedDoctor.setDoctorEmail(doctorRequestDto.getDoctorEmail());
            savedDoctor.setDoctorGender(doctorRequestDto.getDoctorGender());
            savedDoctor.setDoctorMobileNumber(doctorRequestDto.getDoctorMobileNumber());
            savedDoctor.setDoctorPassword(doctorRequestDto.getDoctorPassword());
            savedDoctor.setDoctorSpecialization(doctorRequestDto.getDoctorSpecialization());
            savedDoctor.setHospitalName(doctorRequestDto.getHospitalName());
            savedDoctor.setRegisterDate(doctorRequestDto.getRegisterDate());

            Doctor updatedDoctor = this.iDoctorRepository.save(savedDoctor);
            DoctorRequestDto updatedDoctorRequestDto = modelMapper.map(updatedDoctor, DoctorRequestDto.class);
            return updatedDoctorRequestDto;
        }
        else{
           throw new ControllerException("DOCTOR NOT FOUND WITH EMAIL : " + doctorEmail);
        }

    }

    @Override
    public String deleteDoctor(Long doctorId) {
        Optional<Doctor> existingDoctor = this.iDoctorRepository.findById(doctorId);
        if(existingDoctor.isPresent()){
            this.iDoctorRepository.deleteById(doctorId);
            return "";
        }
        else
            throw new ControllerException("DOCTOR NOT FOUND WITH ID : " + doctorId);
    }

    @Override
    public DoctorRequestDto getDoctorByDoctorId(Long doctorId) {
        Doctor existingDoctor = this.iDoctorRepository.findById(doctorId).orElseThrow(
                () -> new ControllerException("DOCTOR NOT FOUND WITH GIVEN ID : " + doctorId)
        );
//            logger.info("HI");
             DoctorRequestDto doctorRequestDto = modelMapper.map(existingDoctor, DoctorRequestDto.class);
             logger.info(doctorRequestDto.toString());
            return doctorRequestDto;
    }

    @Override
    public List<DoctorRequestDto> getAllDoctors() {
        List<Doctor> doctors = this.iDoctorRepository.findAll();
        List<DoctorRequestDto> doctorRequestDtos = doctors.stream()
                                                    .map((doctors1) -> modelMapper.map(doctors1, DoctorRequestDto.class))
                                                    .collect(Collectors.toList());

        if(doctorRequestDtos.isEmpty())
            throw new ControllerException(" NO DOCTOR FOUND ");
        else
            return doctorRequestDtos;
    }


    @Override
    public DoctorRequestDto getDoctorByEmail(String doctorEmail) {
          Doctor existingDoctor = this.iDoctorRepository.findByDoctorEmail(doctorEmail);
          if (existingDoctor != null) {
              DoctorRequestDto doctorRequestDto = modelMapper.map(existingDoctor, DoctorRequestDto.class);
              return doctorRequestDto;
          }
          else
              throw new ControllerException("DOCTOR NOT AVAILABLE WITH GIVEN EMAIL : " + doctorEmail);
    }

    @Override
    public List<AppointmentRequestDto> findAppointmentsByDoctorEmail(String doctorEmail) {
        Optional<Doctor> optionalDoctor = Optional.ofNullable(this.iDoctorRepository.findByDoctorEmail(doctorEmail));
        if(optionalDoctor.isPresent()){
            List<Appointment> listOfAppointments = this.iAppointmentRepository.findByDoctorEmail(doctorEmail);
//        AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto();
//        BeanUtils.copyProperties(listOfAppointments, appointmentRequestDto);

            List<AppointmentRequestDto> appointmentRequestDtoList = listOfAppointments.stream()
                    .map((appointment -> modelMapper.map(appointment, AppointmentRequestDto.class)))
                    .collect(Collectors.toList());

            return appointmentRequestDtoList;
        }
        else
            throw new ControllerException("DOCTOR NOT EXISTS WITH GIVEN EMAIL : " + doctorEmail + " ANYMORE! ");


    }

}
