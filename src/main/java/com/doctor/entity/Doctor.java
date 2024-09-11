package com.doctor.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.dialect.MySQLDialect;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long doctorId;
    private String role;
    private String doctorEmail;
    private String doctorGender;
    private Long doctorMobileNumber;
    private String doctorName;
    private String doctorPassword;
    private String doctorSpecialization;
    private String hospitalName;
    private Date registerDate;
    private String  resetPasswordToken;
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private  HospitalDetails hospitalDetails;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;


}
