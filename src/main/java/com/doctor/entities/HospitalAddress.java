package com.doctor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital_address")
@Entity
public class HospitalAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long hospitalAddressId;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String state;

    @Column(name = "addres_name", nullable = false)
    private String addressName;

    @Column(nullable = false)
    private String city;

    @Column(name = "zip_code", unique = true, nullable = false)
    private String zipCode;

}
