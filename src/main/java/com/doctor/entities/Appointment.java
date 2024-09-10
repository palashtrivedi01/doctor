package com.doctor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private MultipartFile fileName;

    private Date appointmentDate;

    private String doctorEmail;

    private String doctorName;

    private String patientEmail;

    private Long patientMobileNo;

    private String patientName;

    private LocalDateTime time;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "patientId")
    private Patient patientId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctorId")
    private Doctor doctorId;
}
