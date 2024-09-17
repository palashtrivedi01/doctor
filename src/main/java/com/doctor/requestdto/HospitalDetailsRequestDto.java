package com.doctor.requestdto;

import lombok.Data;

@Data
public class HospitalDetailsRequestDto {
    private String emergency;
    private Long contactNumber;
    private String hospitalName;
    private Integer noOfBeds;
    private Integer noOfIcu;
    private Integer noOfOt;

}
