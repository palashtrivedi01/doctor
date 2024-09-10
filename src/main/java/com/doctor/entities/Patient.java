package com.doctor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "patient_email", unique = true)
    private String patientEmail;

    @Column(name = "patient_mobile_number", unique = true, nullable = false)
    private Long patientMobileNumber;

    @Column(name = "patient_password", nullable = false)
    private String patientPassword;

    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    private String role;

//    @UpdateTimestamp
//    @Column(name = "updated_date")
//    private Timestamp updateDate;

//*******************************************************
    private Date updateDate;

    //appointment
    //contact list both otm

//    @OneToMany(mappedBy = "patients", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "patient_id")
//    private List<Appointment> appointmentList = new ArrayList<>();

//    @OneToMany(mappedBy = "patients", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "patient_id")
//    private List<ContactUs> contactUsList = new ArrayList<>();

}
