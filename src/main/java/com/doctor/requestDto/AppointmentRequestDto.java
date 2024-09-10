package com.doctor.requestDto;

import com.doctor.entities.Doctor;
import com.doctor.entities.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

//@Getter
//@Setter
@Data //Getter Setter ToString EqualsAndHashcode
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {

    private String file;

    private Date appointmentDate;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email is mandatory and  must follow the pattern: abc@abc.com")
    private String doctorEmail;

    private String doctorName;

    private String patientName;

    private String patientEmail;

    @Pattern(regexp = "[6789][0-9]{9}", message = "Enter valid mobile number")
    private String patientMobileNo;

    @CreationTimestamp
    private Timestamp time;

    private Doctor doctors;

    private Patient patients;


}
