package com.doctor.serviceimpl;

import com.doctor.entity.Appointment;
import com.doctor.entity.Doctor;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repository.AppointmentRepo;
import com.doctor.repository.DoctorRepo;
import com.doctor.requestdto.AppointmentRequestDto;
import com.doctor.requestdto.DoctorRequestDto;
import com.doctor.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public DoctorRequestDto addDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor();
        doctor.setDoctorSpecialization(doctorRequestDto.getDoctorSpecialization());
        doctor.setDoctorName(doctorRequestDto.getDoctorName());
        doctor.setDoctorPassword(doctorRequestDto.getDoctorPassword());
        doctor.setDoctorGender(doctorRequestDto.getDoctorGender());
        doctor.setDoctorMobileNumber(doctorRequestDto.getDoctorMobileNumber());
        doctor.setDoctorEmail(doctorRequestDto.getDoctorEmail());
        doctor.setRegisterDate(doctorRequestDto.getRegisterDate());
        doctor.setHospitalName(doctorRequestDto.getHospitalName());
        doctor.setResetPasswordToken(doctorRequestDto.getResetPasswordToken());
        doctor.setRole(doctorRequestDto.getRole());
        doctor.setUpdateDate(doctorRequestDto.getUpdateDate());
        doctorRepo.save(doctor);
        return doctorRequestDto;
    }

    @Override
    public DoctorRequestDto updateDoctor(DoctorRequestDto doctorRequestDto, String doctorEmail) throws BusinessException {
        Doctor byDoctorEmail = doctorRepo.findByDoctorEmail(doctorEmail);
        DoctorRequestDto dto = new DoctorRequestDto();
        if (byDoctorEmail != null) {
            dto.setDoctorName(doctorRequestDto.getDoctorName());
            dto.setDoctorGender(doctorRequestDto.getDoctorGender());
            dto.setDoctorMobileNumber(doctorRequestDto.getDoctorMobileNumber());
            dto.setDoctorPassword(doctorRequestDto.getDoctorPassword());
            dto.setDoctorSpecialization(doctorRequestDto.getDoctorSpecialization());
            dto.setDoctorEmail(doctorEmail);
            dto.setRegisterDate(doctorRequestDto.getRegisterDate());
            dto.setHospitalName(doctorRequestDto.getHospitalName());
            dto.setResetPasswordToken(doctorRequestDto.getResetPasswordToken());
            dto.setRole(doctorRequestDto.getRole());
            dto.setUpdateDate(doctorRequestDto.getUpdateDate());
            doctorRepo.save(byDoctorEmail);
            return dto;
        } else {
            throw new BusinessException("Doctor not found with given Doctor Email : " + doctorEmail);
        }
    }

    @Override
    public DoctorRequestDto getDoctorByDoctorEmail(String doctorEmail) throws ControllerException {
        Doctor byDoctorEmail = doctorRepo.findByDoctorEmail(doctorEmail);
        if (byDoctorEmail != null) {
            DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
            doctorRequestDto.setDoctorEmail(byDoctorEmail.getDoctorEmail());
            doctorRequestDto.setDoctorName(byDoctorEmail.getDoctorName());
            doctorRequestDto.setDoctorPassword(byDoctorEmail.getDoctorPassword());
            doctorRequestDto.setDoctorGender(byDoctorEmail.getDoctorGender());
            doctorRequestDto.setDoctorMobileNumber(byDoctorEmail.getDoctorMobileNumber());
            doctorRequestDto.setDoctorEmail(byDoctorEmail.getDoctorEmail());
            doctorRequestDto.setDoctorSpecialization(byDoctorEmail.getDoctorSpecialization());
            doctorRequestDto.setHospitalName(byDoctorEmail.getHospitalName());
            doctorRequestDto.setResetPasswordToken(byDoctorEmail.getResetPasswordToken());
            doctorRequestDto.setRole(byDoctorEmail.getRole());
            doctorRequestDto.setUpdateDate(byDoctorEmail.getUpdateDate());
            doctorRequestDto.setRegisterDate(byDoctorEmail.getRegisterDate());
            doctorRequestDto.setDoctorGender(byDoctorEmail.getDoctorGender());
            return doctorRequestDto;

        }
        throw new ControllerException("Doctor not found with given Doctor Email : " + doctorEmail);
    }

    @Override
    public DoctorRequestDto getDoctorByDoctorId(Long doctorId) throws ControllerException {
        Doctor doctor = doctorRepo.findById(doctorId).isPresent() ? doctorRepo.findById(doctorId).get() : null;
        if (doctor != null) {
            DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
            doctorRequestDto.setDoctorName(doctor.getDoctorName());
            doctorRequestDto.setDoctorGender(doctor.getDoctorGender());
            doctorRequestDto.setDoctorMobileNumber(doctor.getDoctorMobileNumber());
            doctorRequestDto.setDoctorEmail(doctor.getDoctorEmail());
            doctorRequestDto.setRegisterDate(doctor.getRegisterDate());
            doctorRequestDto.setHospitalName(doctor.getHospitalName());


            doctorRequestDto.setResetPasswordToken(doctor.getResetPasswordToken());
            doctorRequestDto.setRole(doctor.getRole());
            doctorRequestDto.setUpdateDate(doctor.getUpdateDate());
            return doctorRequestDto;
        } else {
            throw new ControllerException("Doctor not found with given Doctor Id: " + doctorId);
        }
    }

    @Override
    public List<DoctorRequestDto> getAllDoctors() throws ControllerException {
        List<Doctor> allDoctors = doctorRepo.findAll();
        if (allDoctors.isEmpty()) {
            throw new ControllerException("No doctors found");
        }
        return allDoctors.stream().map(doctor -> {
            DoctorRequestDto doctorRequestDto = new DoctorRequestDto();
            doctorRequestDto.setDoctorName(doctor.getDoctorName());
            doctorRequestDto.setDoctorGender(doctor.getDoctorGender());
            doctorRequestDto.setDoctorMobileNumber(doctor.getDoctorMobileNumber());
            doctorRequestDto.setDoctorEmail(doctor.getDoctorEmail());
            doctorRequestDto.setRegisterDate(doctor.getRegisterDate());
            doctorRequestDto.setHospitalName(doctor.getHospitalName());
            doctorRequestDto.setResetPasswordToken(doctor.getResetPasswordToken());
            doctorRequestDto.setRole(doctor.getRole());
            doctorRequestDto.setDoctorSpecialization(doctor.getDoctorSpecialization());
            doctorRequestDto.setUpdateDate(doctor.getUpdateDate());
            return doctorRequestDto;
        }).toList();
    }

    @Override
    public String deleteDoctor(Long doctorId) throws ControllerException {
        if (!doctorRepo.existsById(doctorId)) {
            throw new ControllerException("Doctor not found Exception with  given Doctor Id : " + doctorId);
        }
        doctorRepo.deleteById(doctorId);
        return "Doctor deleted successfully";

    }


    @Override
    public List<AppointmentRequestDto> getAppointmentByDoctorEmail(String doctorEmail) throws BusinessException {
        Doctor byDoctorEmail = doctorRepo.findByDoctorEmail(doctorEmail);
        List<Appointment> appointment = appointmentRepo.findByDoctorEmail(doctorEmail);
        if (byDoctorEmail != null && !appointment.isEmpty()) {

            List<AppointmentRequestDto> list= appointment.stream().map(a -> {
                AppointmentRequestDto dto = new AppointmentRequestDto();
                dto.setDoctorName(a.getDoctorName());
                dto.setDoctorEmail(a.getDoctorEmail());
                dto.setTime(a.getTime());
                dto.setDoctorEmail(a.getDoctorEmail());
                dto.setFileAttach(a.getFileAttach());
                dto.setAppointmentDate(a.getAppointmentDate());
                return dto;
            }).toList();
            return list;
        } else {
            throw new BusinessException("Doctor not found Exception with given email : " + doctorEmail);
        }
    }


}
