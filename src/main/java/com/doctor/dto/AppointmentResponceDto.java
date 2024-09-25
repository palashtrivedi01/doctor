package com.doctor.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponceDto {
    @Id
    @NotBlank
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_id;

    private String fileName;

    private String appointmentDate;

//    @Email
    private String doctorEmail;

//    @Column(nullable = false)
    private String doctorEame;

    private String patientEmail;

//    @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be valid with 10 digits")
    private Long patientMobileNo;

    private String patientName;

    private String time;

}
