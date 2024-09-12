package com.doctor.requestdto;


import lombok.Data;

import java.util.Date;
@Data

public class DoctorRequestDto {
    private String role;
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
