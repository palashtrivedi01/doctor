package com.doctor;


import com.doctor.services.IFileService;
import jakarta.annotation.Resource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@OpenAPIDefinition
public class DoctorApplication {

//	@Resource
//	private IFileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(DoctorApplication.class, args);
	}

//	public void run(String... args) throws Exception {
//		System.out.println("ENTRY");
//		fileService.init();
//	}

}
