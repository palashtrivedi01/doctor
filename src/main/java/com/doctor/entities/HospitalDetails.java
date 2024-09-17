package com.doctor.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital_details")
@Entity
public class HospitalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    @Column(name = "hospital_name", nullable = false)
    @Size(min = 3, max = 50, message = "Hospital name must contain min 3 and max 50 characters")
    private String hospitalName;

    private String emergency;

    @Column(name = "contact_number", unique = true, nullable = false)
    private Long contactNumber;

    @Column(name = "no_of_beds")
    @Min(value = 10, message = "no of beds must be at least 10")
    private Integer noOfBeds;

    @Column(name = "no_of_icu")
    @Min(value = 5, message = "no of ICUs must be at least 5")
    private Integer noOfIcu;

    @Column(name = "no_of_ot")
    @Min(value = 5, message = "no of OTs must be at least 5")
    private Integer noOfOt;


    //    @OneToMany(mappedBy = "hospital_details", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "hospital_id")
@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinColumn(name = "hospital_address_id")
    private HospitalAddress hospitalAddress;

//************************************

    //doctors list
//    @OneToMany(mappedBy = "doctors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "doctor_id")
//    private List<Doctor> doctors = new ArrayList<>();


}
