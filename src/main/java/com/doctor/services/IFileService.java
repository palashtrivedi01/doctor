package com.doctor.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileService {

    String storeFile(MultipartFile file);

    InputStream getResource(String path, String fileName) throws FileNotFoundException;

    Resource loadFileAsResource(String fileName) throws MalformedURLException;



}
