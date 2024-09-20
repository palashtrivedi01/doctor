package com.doctor.requestDto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDto {

    private String name;

    @Column(unique = true, nullable = false)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            message = "Email is mandatory and  must follow the pattern: abc@abc.com")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain atleast one digit, one lowercase letter, one uppercase letter, one special character, no whitespace and must be atleast 8 characters long!")
    private String password;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "[6789][0-9]{9}", message = "Enter valid mobile number")
    private String mobile;

    private String role;

}
