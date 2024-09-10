package com.doctor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
@Data
@Entity
public class Patient {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patient_id;

    private String patient_email;

    private Long patient_mobile_number;

    private String patient_name;

    private String patient_password;

    private LocalDate register_date;

    private String reset_password_token;

    private String role;

    private LocalDate updated_date;

}
