package com.doctor.entities;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String role;

    private String doctorEmail;

    private String doctorGender;

    private Long doctorMobileNumber;

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
