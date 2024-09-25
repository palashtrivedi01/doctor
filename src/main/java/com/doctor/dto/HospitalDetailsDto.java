package com.doctor.dto;
import lombok.Data;

@Data
public class HospitalDetailsDto {
    private  String emergency;

    private  Integer contactNumber;

    private String hospitalName;

    private Integer noOfBeds;

    private Integer noOfIcu;

    private Integer noOfOt;

}
