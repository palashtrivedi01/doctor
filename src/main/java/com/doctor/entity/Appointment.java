package com.doctor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
//@Getter
//@Setter

@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private String file;
    private Date appointmentDate;
    private String doctorEmail;
    private String doctorName;
    private String patientEmail;
    private String patientName;
    private Timestamp time;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "patientId")
    private Patient patient;
}
