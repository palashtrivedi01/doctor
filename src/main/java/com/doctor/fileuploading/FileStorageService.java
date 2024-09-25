package com.doctor.fileuploading;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

public interface FileStorageService {
    String storeFile(MultipartFile file);

    InputStream downloadFile(String filepath) throws FileNotFoundException;

     Resource loadFileAsResource(String fileName) throws MalformedURLException, NotFoundException;
}
