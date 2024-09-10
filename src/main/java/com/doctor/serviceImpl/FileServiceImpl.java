package com.doctor.serviceImpl;

import com.doctor.repositories.IAppointmentRepository;
import com.doctor.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Override
    public String saveFile(MultipartFile file) {

        this.iAppointmentRepository.

        return "";
    }
}
