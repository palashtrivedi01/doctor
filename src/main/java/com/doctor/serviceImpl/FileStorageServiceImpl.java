package com.doctor.serviceImpl;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.doctor.exception.BusinessException;
import com.doctor.requestDto.AppointmentRequestDto;
import com.doctor.services.IFileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.exception.FileStorageException;
import com.doctor.services.IFileService;
import com.doctor.util.FileStorageProperties;

//import javassist.NotFoundException;

@Service
public class FileStorageServiceImpl implements IFileService {

    private final Path fileStorageLocation;
    private String filePath = "image/";

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()+filePath)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.");
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!");
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws MalformedURLException {

        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if(resource.exists()) {
            return resource;
        } else {
            throw new FileStorageException("File not found " + fileName);
        }

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        System.out.println("PATH:"+path);
        String fullPath = path + File.separator + fileName;
        System.out.println("FULLPATH : "+fullPath);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fullPath);
        return inputStream;
    }



}