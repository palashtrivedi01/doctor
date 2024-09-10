package com.doctor.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AppointmentRequestDto {
    private int doctorId;
    private Long appointment_id;

    private String file;

    private LocalDate appointment_date;

    private String doctor_email;

    private String doctor_name;

    private String patient_email;

    private Long patient_mobile_number;

    private String patient_name;

    private LocalDate time;

    private List<String> fileNames;
}
