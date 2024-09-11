package com.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class DoctorRequestDto {

    private String doctorEmail;

    private String doctorGender;

    private Long doctorMobileNumber;

    private String doctorName;

    private String doctorPassword;

    private String doctorSpecialization;

    private String hospitalName;

    private Date registerDate;

    private String resetPasswordToken;

    private Date updateDate;
}
