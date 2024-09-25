package com.doctor.dto;
import com.doctor.enums.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Column(unique = true)
    private  String email;

    @Column(unique = true)
    private Long mobile;

    private  String password;
    private Role role;

}