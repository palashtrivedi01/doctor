package com.doctor.serviceImpl;

import com.doctor.repositories.IAppointmentRepository;
import com.doctor.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements IFileService {

    //    @Autowired
//    private IAppointmentRepository iAppointmentRepository;

    /*private final Path path = Paths.get("uploads");



    @Override
    public void saveFile(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.path.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
*/


    @Override
    public String uploadFile(MultipartFile file, String path) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = filename+extension;
        String fullPathWithFileName = path+fileNameWithExtension;

        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))
        {
            File folder = new File(path);
            if(!folder.exists())
            {
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
        }

        return fileNameWithExtension;
    }


}
