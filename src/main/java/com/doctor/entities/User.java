package com.doctor.entities;

import com.doctor.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    private Long mobile;

    private String password;

    private Role role;

    @OneToOne
    @JsonIgnore
    private Doctor doctorId;

    @OneToOne
    @JsonIgnore
    private Patient patient_id;
}
