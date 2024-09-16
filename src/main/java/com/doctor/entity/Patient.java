package com.doctor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  patientId;
    private String patientEmail;
    private Long patientMobileNumber;
    private String patientName;
    private String patientPassword;
    private Date registerDate;
    private String resetPasswordToken;
    private String role;
    private Date updateDate;

//    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
//    private List<Appointment> appointments;



}
