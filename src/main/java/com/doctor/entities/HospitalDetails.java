package com.doctor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    private String emergency;

    private Long contactNumber;

    private String hospitalName;

    private Integer noOfBeds;

    private Integer noOfIcu;

    private Integer noOfOt;

    @OneToMany
    private List<HospitalAddress> hospitalAddressAddressId;

}
