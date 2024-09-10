package com.doctor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(name = "role")
    private String role;

    @Column(name = "email", unique = true, nullable = false)
    private String doctorEmail;

    @Column(name = "gender")
    private String doctorGender;

    @Column(name = "mobile_number", unique = true, nullable = false)
    private Long doctorMobileNumber;

    @Column(name = "name", nullable = false)
    private String doctorName;

    @Column(name = "password", nullable = false)
    private String doctorPassword;

    @Column(name = "specialization")
    private String doctorSpecialization;

    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;

    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "updatedDate")
    private Date updateDate;

    //by default, fetch type lazy hi hota hai, so not necessary to use
//    @OneToOne(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //OneToOne since one doctor can work in only one hospital
//    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "doctor_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private HospitalDetails hospitalDetails;


//******************************************
    //user
    //appointment list type


//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Users user;

//    @OneToMany(mappedBy = "doctors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "doctor_id")
//    private List<Appointment> appointments = new ArrayList<>();



}
