package com.doctor.entity;

import com.doctor.Enum.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private Long mobile;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId")
    private Patient patient;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }



}
