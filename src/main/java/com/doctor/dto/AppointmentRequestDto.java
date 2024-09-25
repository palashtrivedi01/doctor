package com.doctor.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentRequestDto {

//    private String file;
    private String fileAttech;

    private String appointmentDate;

//    @Email
//    @Pattern(regexp = ".+@.+\\..+", message = "Email should be in proper foramat")
    private String doctorEmail;

    private String doctorName;

//    @Email
//    @Pattern(regexp = ".+@.+\\..+",message = "Email should be in proper format.")
    private String patientEmail;

//    @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be valid with 10 digits")
    private Long patientMobileNo;

    private String patientName;

    private String time;

//
//    public void setFileAttech(String fileAttech) {
//        this.fileAttech=fileAttech;
//    }
}
