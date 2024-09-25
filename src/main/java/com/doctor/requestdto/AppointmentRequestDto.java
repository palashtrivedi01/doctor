package com.doctor.requestdto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.Date;
@Data

public class AppointmentRequestDto {

    private Long appointmentId;
    private String appointmentDate;
    private String doctorEmail;
    private String doctorName;
    private String patientEmail;
    private String patientName;
    private String time;
    private String fileAttach;
}
