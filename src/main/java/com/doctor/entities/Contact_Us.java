package com.doctor.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.repository.NoRepositoryBean;
@Data

@Entity
public class Contact_Us {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    @Column(unique = true)
    private String email;
    private String fullName;
    @Column(unique = true)
    private Integer mobileNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId")
    private Patient patientId;

}