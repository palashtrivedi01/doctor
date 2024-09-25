package com.doctor.dto;

import com.doctor.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class DoctorRequestDTO {

    private Role role;
    private String doctorEmail;
    private String doctorGender;
    private String doctorMobileNumber;
    private String doctorName;
    private String doctorPassword;
    private String doctorSpecialization;
    private String hospitalName;
    private Date registerDate;

    private String resetPasswordToken;
    private Date updateDate;

}
