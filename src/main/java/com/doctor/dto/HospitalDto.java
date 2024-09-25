package com.doctor.dto;

import lombok.Data;

@Data
public class HospitalDto {

    private String emergency;

    private Long contactNumber;

    private String hospitalName;

    private Integer noOfBeds;

    private Integer noOfIcu;

    private Integer noOfOt;

}
