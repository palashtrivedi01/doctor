package com.doctor.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data


public class AppointmentResponseDto {
//    private Long appointmentId;
    private String file;
    private Date appointmentDate;
    private String doctorEmail;
    private String doctorName;
    private String patientEmail;
    private String patientName;
    private Timestamp time;
}
