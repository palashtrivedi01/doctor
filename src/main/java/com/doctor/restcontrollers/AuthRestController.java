package com.doctor.restcontrollers;

import com.doctor.entities.JwtUser;
import com.doctor.entities.Users;
import com.doctor.requestDto.LoginUserDto;
import com.doctor.requestDto.RegisterUserDto;
import com.doctor.responseDto.LoginResponse;
import com.doctor.security.AuthenticationService;
import com.doctor.services.IUserService;
import com.doctor.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthRestController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    /*@PostMapping("/signup")
    public ResponseEntity<JwtUser> register(@RequestBody RegisterUserDto registerUserDto) {
        JwtUser registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }*/

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        Users authenticatedUser = authenticationService.authenticate(loginUserDto);
        System.out.println("Enter");
        String jwtToken = jwtUtil.generateToken(authenticatedUser);

//        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtUtil.getExpirationTime());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtUtil.getExpirationTime());
        log.info("Successful login");
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        System.out.println("A");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("B");
        User currentUser = (User) authentication.getPrincipal();
        System.out.println("ROLE : " + currentUser.getAuthorities());
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> allUsers() {
        List <Users> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }



}
