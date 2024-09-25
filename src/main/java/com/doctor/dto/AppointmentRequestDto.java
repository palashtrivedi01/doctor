package com.doctor.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {
//    private int doctorId;
//    private Long appointmentId;
//
//    private String file;

    private String appointmentDate;

    private String doctorEmail;

    private String doctorName;

    private String patientEmail;

    private Long patientMobileNumber;

    private String patientName;

    private String time;

    private  String fileAttech;

    @Lob
    private byte[] data;



}
