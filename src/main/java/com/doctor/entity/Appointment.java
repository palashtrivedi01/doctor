package com.doctor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private String file;
    private String appointmentDate;
    private String doctorEmail;
    private String doctorName;
    private String patientEmail;
    private String patientName;
    private String time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId")
    private Patient patient;

}
