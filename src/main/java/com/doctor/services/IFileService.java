package com.doctor.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileService {

//    void saveFile(MultipartFile file);

    String uploadFile(MultipartFile file, String path) throws Exception;





}
