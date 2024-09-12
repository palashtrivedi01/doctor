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
     private long hospitalId;

    private  String emergency;

    @Column(unique=true)
    private  Integer contactNumber;

    private String hospitalName;

    private Integer noOfBeds;

    private Integer noOfIcu;

    private Integer noOfOt;

    @Column(name="hospital_address")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HospitalAddress> hospital_address_address_id;

   }
