package com.doctor.fileuploading;


import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;


@Service
public class FileStorageServiceImpl implements FileStorageService{
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
    public InputStream downloadFile( String filepath) throws FileNotFoundException {

        System.out.println("FilePath "+filePath);

        String fullPath = filePath+ File.separator ;

        System.out.println("FilePath "+filePath);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fullPath);
        return inputStream;
    }

//    //@Override
//    public Resource loadFileAsResource(String fileName) throws MalformedURLException, NotFoundException {
//
//        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//        if(resource.exists()) {
//            return resource;
//        } else {
//            throw new NotFoundException("File not found " + fileName);
//        }
//
//    }

    @Override
    public Resource loadFileAsResource(String fileName) throws MalformedURLException, NotFoundException {

        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if(resource.exists()) {
            return resource;
        } else {
            throw new NotFoundException("File not found " + fileName);
        }

    }


}