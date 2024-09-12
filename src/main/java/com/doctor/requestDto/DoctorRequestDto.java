package com.doctor.requestDto;

import com.doctor.entities.HospitalDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {


    private String role;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email is mandatory and  must follow the pattern: abc@abc.com")
    private String doctorEmail;

    private String doctorGender;

    @Pattern(regexp = "[6789][0-9]{9}", message = "Enter valid mobile number")
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
