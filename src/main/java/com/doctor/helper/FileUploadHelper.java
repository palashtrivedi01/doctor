//package com.doctor.helper;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//@Component
//public class FileUploadHelper {
//    private final String UPLOAD_DIR = new ClassPathResource("target/upload/").getFile().getAbsolutePath();
//    public FileUploadHelper() throws IOException {
//    }
//    public boolean uploadFile(MultipartFile file){
//        boolean f = false;
//        try {
//            InputStream is = file.getInputStream();
//            byte data[] = new byte[is.available()];
//            is.read(data);
//
//            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+ File.separator+file.getOriginalFilename());
//            fos.write(data);
//
//            fos.flush();
//            fos.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return f;
//    }
//
//}
//
////Override
////public AppointmentResponceDto saveAppoinment(AppointmentRequestDto appointmentRequestDto, MultipartFile file) throws IOException {
////    String fileName = null;
////    if(file!= null && !file.isEmpty()){
////        fileName=saveFile(file);
////        appointmentRequestDto.setFile(file);
////    }
////    Appointment appointment = modelMapper.map(appointmentRequestDto,Appointment.class);
////
////    Appointment saveedAppoinment = appointmentRepository.save(appointment);
////
////    AppointmentResponceDto responceDto= modelMapper.map(saveedAppoinment,AppointmentResponceDto.class);
////
////    return responceDto;
////}
////private String saveFile (MultipartFile file) throws IOException{
////    String originalFileName = file.getOriginalFilename();
////    String uniqueFileName = System.currentTimeMillis() + " " + originalFileName;
////    Path filePath = Paths.get(uploadDirectory,uniqueFileName);
////    Files.createDirectories((filePath.getParent()));
////    Files.write(filePath,file.getBytes());
////    return  uniqueFileName;
////}
////}
//
//
//
