package com.doctor.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HospitalDetailsRequestDto {

    @Column(name = "hospital_name", nullable = false)
    @Size(min = 3, max = 50, message = "Hospital name must contain min 3 and max 50 characters")
    private String hospitalName;

    private String emergency;

    @Column(name = "contact_number", unique = true, nullable = false)
    private Long contactNumber;

    @Column(name = "no_of_beds")
    @Min(value = 10, message = "no of beds must be at least 10")
    private Integer noOfBeds;

    @Column(name = "no_of_icu")
    @Min(value = 5, message = "no of ICUs must be at least 5")
    private Integer noOfIcu;

    @Column(name = "no_of_ot")
    @Min(value = 5, message = "no of OTs must be at least 5")
    private Integer noOfOt;

}
