package com.doctor.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.lang.annotation.Native;
@Data
@Entity

public class HospitalAddress {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    private String country;

    private  String state;

    private String addressName;

    private String city;

    private  String zipCode;


}
