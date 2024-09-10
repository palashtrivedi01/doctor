package com.doctor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Blob;
import java.time.LocalDate;
@Data
@Entity
public class Appointment {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_id;

    private String file;

    private LocalDate appointment_date;

    private String doctor_email;

    private String doctor_name;

    private String patientEmail;

    private Long patient_mobile_number;

    private String patient_name;

    private LocalDate time;

    @ManyToOne
    @JsonIgnore
    private Doctor doctor_id;

    @ManyToOne
    @JsonIgnore
    private Patient patient_id;


}
