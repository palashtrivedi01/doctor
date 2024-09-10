package com.doctor.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class HospitalDetails {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long hospital_id;

    private  String emergency;

    private  Integer contact_number;

    private String hospital_name;

    private Integer no_of_beds;

    private Integer no_of_icu;

    private Integer no_of_ot;

    @Column(name="hospital_address")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HospitalAddress> hospital_address_address_id;

   }
