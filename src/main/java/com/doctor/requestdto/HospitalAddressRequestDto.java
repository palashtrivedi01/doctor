package com.doctor.requestdto;

import lombok.Data;

@Data
public class HospitalAddressRequestDto {
    private String country;
    private String state;
    private String addressName;
    private String city;
    private String zipCode;
}
