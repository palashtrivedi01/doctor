package com.doctor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "hospital_details")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private HospitalAddress hospitalAddress;

//    @OneToMany(mappedBy = "HospitalDetails")
//    private List<Doctor> doctors;


}
