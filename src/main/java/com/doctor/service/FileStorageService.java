//package com.doctor.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import java.nio.file.Path;
//
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.UUID;
//
//@Service
//public class FileStorageService {
//    private  final Path fileStorageLocation= Paths.get("uploads");
//
//    public FileStorageService(){
//        try{
//            Files.createDirectories(this.fileStorageLocation);
//        }catch (Exception e){
//            throw  new RuntimeException("Error"+e);
//        }
//    }
//
//
//    public String storeFile(MultipartFile file) {
//        String fileName = UUID.randomUUID().toString() +"_" + file.getOriginalFilename();
//        try {
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation. StandardCopyOption.REPLACE_EXISTING);
//            return  fileName;
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//}
