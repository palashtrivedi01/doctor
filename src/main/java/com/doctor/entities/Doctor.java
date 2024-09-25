package com.doctor.entities;

import com.doctor.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Doctor {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long doctorId;
    private Role role;

//    @Column(unique = true)
    private String doctorEmail;

    private String doctorGender;

//    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
//    @Column(unique = true)
    private String doctorMobileNumber;

    private String doctorName;
    private String doctorPassword;
    private String doctorSpecialization;
    private String hospitalName;
    private Date registerDate;

    private String resetPasswordToken;
    private Date updateDate;

    @ManyToOne
    @JsonIgnore
    private HospitalDetails hospital_id;

   /* @ManyToOne
    private ArrayList<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    private Users users;*/
}
