package com.doctor.entities;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.aspectj.bridge.Message;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String role;

//    @Email(message = "Wrong email address ")
    private String doctorEmail;

    private String doctorGender;

    @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be valid with 10 digits")
    private String doctorMobileNumber;

    private String doctorName;

    private String doctorPassword;

    private String doctorSpecialization;

    private String hospitalName;

    private Date registerDate;

    private String resetPasswordToken;

    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private HospitalDetails hospitalId;

     @ManyToOne
     @JoinColumn(name = "userId")
     private User user;


}
