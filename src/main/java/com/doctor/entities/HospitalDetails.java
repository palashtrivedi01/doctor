package com.doctor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDetails {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long hospitalId;

    private  String emergency;

    @Column(unique=true)
    private  Integer contactNumber;

    private String hospitalName;

    private Integer noOfBeds;

    private Integer noOfIcu;

    private Integer noOfOt;

    @Column(name="hospital_address")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HospitalAddress> addressId;

   }
