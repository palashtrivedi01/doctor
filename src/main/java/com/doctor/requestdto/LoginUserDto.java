package com.doctor.requestdto;

import com.doctor.Enum.Roles;
import lombok.Data;

@Data
public class LoginUserDto {
    private String email;

    private String password;
    private Roles role;
}
