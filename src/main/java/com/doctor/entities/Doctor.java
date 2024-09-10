package com.doctor.entities;

import com.doctor.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long doctor_id;
    private Role role;
    private String doctor_email;
    private String doctor_gender;
    private String doctor_mobile_number;
    private String doctor_name;
    private String doctor_password;
    private String doctor_specialization;
    private String hospital_name;
    private Date register_date;

    private String reset_password_token;
    private Date update_date;

    @ManyToOne
    private HospitalDetails hospital_id;

   /* @ManyToOne
    private ArrayList<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    private Users users;*/
}
