package com.doctor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
private String  token;
private  Long expiresIn;

public  String getToken()
{
    return token;
}
}
