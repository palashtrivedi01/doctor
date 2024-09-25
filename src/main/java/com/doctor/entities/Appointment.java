package com.doctor.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private String fileAttech;

    private String appointmentDate;

//    @Email(message = "Enter valid mail")
    private String doctorEmail;

    private String doctorName;

    private String patientEmail;

    private Long patientMobileNo;

    private String patientName;

    private String time;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "patientId")
    private Patient patientId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctorId")
    private Doctor doctorId;
}
