package com.doctor.responseDto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserResponseDto {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String mobile;

    private String role;

}
