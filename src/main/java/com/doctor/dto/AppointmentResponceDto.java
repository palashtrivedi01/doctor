package com.doctor.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponceDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_id;

    private String file;

    private Date appointment_date;

    private String doctor_email;

    private String doctor_name;

    private String patient_email;

    private Long patient_mobile_no;

    private String patient_name;

    private LocalDateTime time;

}
