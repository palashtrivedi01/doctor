package com.doctor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "hospitalAddress")
public class HospitalAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String country;
    private String state;
    private String addressName;
    private String city;
    private String zipCode;

}
