package com.doctor.dto;

import lombok.Data;

@Data
public class HospitalAddressDto {
    private Long addressId;

    private String country;

    private String state;

    private String addressName;

    private String city;

    private String zipCode;

}
