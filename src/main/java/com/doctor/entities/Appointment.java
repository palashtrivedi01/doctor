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
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private String file;

    private LocalDate appointmentDate;

    private String doctorEmail;

    private String doctorName;
    @Column(unique = true)
    private String patientEmail;

    private Long patientMobileNumber;

    private String patientName;

    private LocalDate time;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctorId")
    private Doctor doctorId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "patientId")
    private Patient patientId;


}
