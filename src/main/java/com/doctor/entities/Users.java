package com.doctor.entities;

import com.doctor.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private  String email;

    @Column(unique = true)
    private Long mobile;

    private  String password;

    private Role role;

   @OneToOne
    private Doctor doctor_id;

  @OneToOne
    private Patient patient_id;




}
