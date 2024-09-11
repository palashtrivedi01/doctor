package com.doctor.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {
    private Long appointment_id;
    private String file;
    private Date appointment_date;
    private String doctor_email;
    private String doctor_name;
    private String patient_email;
    private String patient_name;
    private Timestamp time;

}
