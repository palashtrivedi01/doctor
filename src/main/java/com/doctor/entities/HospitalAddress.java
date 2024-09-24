package com.doctor.entities;

import com.doctor.ENUM.Country;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital_address")
@Entity
@Builder
public class HospitalAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long hospitalAddressId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "State name must contain min 2 and max 50 characters")
    private String state;

    @Column(name = "addres_name", nullable = false)
    @Size(min = 3, max = 100, message = "Address must contain min 3 and max 100 characters")
    private String addressName;

    @Column(nullable = false)
    @Size(min = 3, max = 30, message = "City name must contain min 3 and max 30 characters")
    private String city;

    @Column(name = "zip_code", nullable = false, length = 6)
    private String zipCode;

}
