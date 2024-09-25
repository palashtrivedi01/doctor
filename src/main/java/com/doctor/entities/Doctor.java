package com.doctor.entities;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
//    public Doctor(long m, java.lang.String string3, java.lang.String string4, java.lang.String string5, long n) {
//        // TODO Auto-generated constructor stub
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String role;

    // @Email(message = "Wrong email address ")
    private String doctorEmail;

    private String doctorGender;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be valid with 10 digits")
    private String doctorMobileNumber;

    private String doctorName;

    private String doctorPassword;

    private String doctorSpecialization;

    private String hospitalName;

    private Date registerDate;

    private String resetPasswordToken;

    private Date updateDate;

    @ManyToOne
    @Transient
    @JoinColumn(name = "hospitalId")
    private HospitalDetails hospitalId;

    @ManyToOne
    @Transient
    @JoinColumn(name = "userId")
    private User user;

}
