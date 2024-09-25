package com.doctor.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Entity
@Data
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    private String role;
    @Column(unique = true)
    private String doctorEmail;
    private String doctorGender;
    private Long doctorMobileNumber;
    private String doctorName;
    private String doctorPassword;
    private String doctorSpecialization;
    private String hospitalName;
    private String registerDate;
    private String resetPasswordToken;
    private String updateDate;

    @ManyToOne()
    @JoinColumn(name = "hospitalId")
    @JsonIgnore
    private HospitalDetails hospitalDetails;

//    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Appointment> appointments;


}
