package com.doctor.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {

    private MultipartFile file;

    private Date appointmentDate;

    private String doctorEmail;

    private String doctorName;

    private String patientEmail;

    private Long patientMobileNo;

    private String patientName;

    private LocalDateTime time;

}
