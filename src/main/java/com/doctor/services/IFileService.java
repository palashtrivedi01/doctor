package com.doctor.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileService {

//    void saveFile(MultipartFile file);

//    String uploadFile(MultipartFile file, String path) throws Exception;

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName) throws MalformedURLException, NotFoundException;




}
