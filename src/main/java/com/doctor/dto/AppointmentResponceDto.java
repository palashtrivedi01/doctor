package com.doctor.dto;

import jakarta.persistence.Lob;

import java.time.LocalDate;
import java.util.List;

public class AppointmentResponceDto {

    private int doctorId;
    private Long appointmentId;

    private String fileAttech;


    private String appointmentDate;

    private String doctorEmail;

    private String doctorName;

    private String patientEmail;

    private Long patientMobileNumber;

    private String patientName;

    private String time;

    @Lob
    private byte[] data;


    //private List<String> fileNames;
}
