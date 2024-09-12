package com.doctor.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AppointmentRequestDto {

    private String file;

    private Date appointmentDate;

    @Email
    private String doctorEmail;

    private String doctorName;

    @Email
    private String patientEmail;

    @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be valid with 10 digits")
    private Long patientMobileNo;

    private String patientName;

    private LocalDateTime time;

}
