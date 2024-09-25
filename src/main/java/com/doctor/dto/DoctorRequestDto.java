package com.doctor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {
    private Long doctorId;


//    @NotBlank(message = "Email is mandatory")
//    @Pattern(regexp = ".+@.+\\..+", message = "Email should be in proper foramat")
    private String doctorEmail;

//    @NotBlank
    private String doctorGender;

//    @Pattern(regexp = "^[0-9]{10}$",message = "Mobile number should be valid with 10 digits")
    private String doctorMobileNumber;

//    @NotBlank(message = "Invalid name : Empty name")
//    @NotNull(message = "Invalid name : Name is null")
    private String doctorName;

    private String doctorPassword;

    private String doctorSpecialization;

    private String hospitalName;

    private Date registerDate;

    private String resetPasswordToken;

    private Date updateDate;
}
