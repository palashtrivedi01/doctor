package com.doctor.dto;

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
public class AppointmentResponceDto {
    @Id
    @NotBlank
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_id;

    private String fileName;

    private Date appointment_date;

    @Email
    private String doctor_email;

    private String doctor_name;

    @Email
    private String patient_email;

    @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be valid with 10 digits")
    private Long patient_mobile_no;

    private String patient_name;

    private LocalDateTime time;

}
