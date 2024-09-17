package com.doctor.services;

import com.doctor.exception.FileStorageException;
import com.doctor.exception.NotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

public interface FileStorageService {

    String storeFile(MultipartFile file) throws FileStorageException;

    Resource loadFileAsResource(String fileName) throws MalformedURLException, NotFoundException;
    InputStream getResource(String path, String fileName)throws FileNotFoundException;

}
