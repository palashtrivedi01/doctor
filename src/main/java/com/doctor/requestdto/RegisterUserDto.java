package com.doctor.requestdto;

import com.doctor.Enum.Roles;
import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;

    private String password;

    private Roles role;
}
