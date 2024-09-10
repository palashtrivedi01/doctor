package com.doctor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    private String patientEmail;

    private Long patientMobileNumber;

    private String patientName;

    private String patientPassword;

    private Date registerDate;

    private String resetPasswordToken;

    private String role;

    private Date updatedDate;

    // @ManyToOne
    // private Doctor doctor;
}
