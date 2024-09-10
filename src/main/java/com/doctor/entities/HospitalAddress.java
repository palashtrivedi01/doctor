package com.doctor.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private long address_id;

    private String country;

    private  String state;

    private String address_name;

    private String city;

    private  String zip_code;


}
