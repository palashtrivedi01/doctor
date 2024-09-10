package com.doctor.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactId;

    private String email;

    private String fullName;

    private Long mobileNumber;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patientId;

}
