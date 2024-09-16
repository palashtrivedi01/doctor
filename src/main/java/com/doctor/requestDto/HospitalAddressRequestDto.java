package com.doctor.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalAddressRequestDto {

    private String country;

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
