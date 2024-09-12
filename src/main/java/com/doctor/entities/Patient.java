package com.doctor.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
@Data
@Entity
public class Patient {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    @Column(unique = true)
    private String patientEmail;
    @Column(unique = true)
    private Long patientMobileNumber;

    private String patientName;

    private String patientPassword;

    private LocalDate registerDate;

    private String resetPasswordToken;

    private String role;

    private LocalDate updatedDate;

}
