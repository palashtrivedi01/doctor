package com.doctor.security;

import com.doctor.entities.JwtUser;
import com.doctor.entities.Users;
import com.doctor.repositories.IUserRepository;
import com.doctor.requestDto.LoginUserDto;
import com.doctor.requestDto.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    /*public Users signup(RegisterUserDto input) {
        Users user = new Users();
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return iUserRepository.save(user);
    }*/

    public Users authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return iUserRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }




}
