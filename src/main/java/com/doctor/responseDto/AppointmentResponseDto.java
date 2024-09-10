package com.doctor.responseDto;

import com.doctor.entities.Doctor;
import com.doctor.entities.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

    private String file;

    private Date appointmentDate;

    private String doctorEmail;

    private String doctorName;

    private String patientName;

    private String patientEmail;

    private String patientMobileNo;

    private Timestamp time;

    private Doctor doctors;

    private Patient patients;

}
