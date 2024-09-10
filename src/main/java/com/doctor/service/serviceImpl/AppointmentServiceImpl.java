package com.doctor.service.serviceImpl;

import com.doctor.dto.AppointmentRequestDto;
import com.doctor.dto.AppointmentResponceDto;
import com.doctor.entities.Appointment;
import com.doctor.repository.AppointmentRepository;
import com.doctor.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Override
    public Appointment findByEmail(String patientEmail) {
        Optional<Appointment> byPatientEmail = appointmentRepository.findByPatientEmail(patientEmail);
        return byPatientEmail.orElse(null);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        Appointment save = this.appointmentRepository.save(appointment);
        return save;
    }

    @Override
    public AppointmentResponceDto saveAppoinment(AppointmentRequestDto appointmentRequestDto, MultipartFile file) throws IOException {
        String fileName = null;
        if(file!= null && !file.isEmpty()){
            fileName=saveFile(file);
            appointmentRequestDto.setFile(file);
        }
        Appointment appointment = modelMapper.map(appointmentRequestDto,Appointment.class);

        Appointment saveedAppoinment = appointmentRepository.save(appointment);

        AppointmentResponceDto responceDto= modelMapper.map(saveedAppoinment,AppointmentResponceDto.class);

        return responceDto;
    }
    private String saveFile (MultipartFile file) throws IOException{
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = System.currentTimeMillis() + " " + originalFileName;
        Path  filePath = Paths.get(uploadDirectory,uniqueFileName);
        Files.createDirectories((filePath.getParent()));
        Files.write(filePath,file.getBytes());
        return  uniqueFileName;
    }
}
