package com.doctor.requestDto;

import com.doctor.entities.Doctor;
import com.doctor.entities.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

//@Getter
//@Setter
@Data //Getter Setter ToString EqualsAndHashcode RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDto {

//    private String file;

    private String fileAttach;

    private Date appointmentDate;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email is mandatory and  must follow the pattern: abc@abc.com")
    private String doctorEmail;

    @Size(min = 3, max = 100, message = "Name must contain min 3 and max 100 characters")
    private String doctorName;

    @Size(min = 3, max = 100, message = "Name must contain min 3 and max 100 characters")
    private String patientName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email is mandatory and  must follow the pattern: abc@abc.com")
    private String patientEmail;

    @Pattern(regexp = "[6789][0-9]{9}", message = "Enter valid mobile number")
    private String patientMobileNo;

//    @CreationTimestamp
//    private Timestamp time;

    private String time;


//    private Doctor doctors;

//    private Patient patients;

//    ***********************************************************************

//    private List<String> imageNames;

}
