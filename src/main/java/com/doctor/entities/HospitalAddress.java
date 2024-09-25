package com.doctor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.lang.annotation.Native;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String country;

    private  String state;

    private String addressName;

    private String city;

    private  String zipCode;


}
