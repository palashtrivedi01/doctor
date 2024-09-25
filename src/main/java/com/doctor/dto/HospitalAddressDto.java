package com.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalAddressDto {

    private String country;

    private String state;

    private String addressName;

    private String city;

    private Integer zipCode;
}
