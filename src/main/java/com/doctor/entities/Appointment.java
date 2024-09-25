package com.doctor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Blob;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {
    @Id
//    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private String fileAttech;

    private String appointmentDate;

    private String doctorEmail;

    private String doctorName;
//    @Column(unique = true)
    private String patientEmail;

    private Long patientMobileNumber;

    private String patientName;

    private String time;
    @Lob
    private byte[] data;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctorId")
    private Doctor doctorId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "patientId")
    private Patient patientId;


}
