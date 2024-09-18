package com.doctor.responseDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginResponse {

    private String token;

    private long expiresIn;

}
