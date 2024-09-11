package com.doctor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    private String email;
    private String fullName;
    private Long mobileNumber;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

}
