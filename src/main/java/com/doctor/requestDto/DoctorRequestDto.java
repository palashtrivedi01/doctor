package com.doctor.requestDto;

import com.doctor.entities.HospitalDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private HospitalDetails hospitalDetails;

}
