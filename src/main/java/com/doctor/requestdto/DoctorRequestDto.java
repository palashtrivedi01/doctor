package com.doctor.requestdto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data

public class DoctorRequestDto {
    private Long doctorId;
    private String role;
    private String doctorEmail;
    private String doctorGender;
    private Long doctorMobileNumber;
    private String doctorName;
    private String doctorPassword;
    private String doctorSpecialization;
    private String hospitalName;
    private String registerDate;
    private String resetPasswordToken;
    private String updateDate;
}
