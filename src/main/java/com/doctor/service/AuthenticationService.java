//package com.doctor.service;
//
//import com.doctor.dto.UserDto;
//import com.doctor.entities.Users;
//import com.doctor.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AuthenticationService {
//    private final UserRepository userRepository;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationService(
//            UserRepository userRepository,
//            AuthenticationManager authenticationManager,
//            PasswordEncoder passwordEncoder
//    ) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//    public Users signup(UserDto registerUserDto) {
//        // Check if the user already exists
//        Optional<Users> existingUser = userRepository.findByEmail(registerUserDto.getEmail());
////        if (existingUser.isPresent()) {
////            throw new UserAlreadyExistsException("User with email already exists");
////        }
//
//        // Proceed with registration
//        Users user = new Users();
//        user.setEmail(registerUserDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
//        user.setRole(registerUserDto.getRole());
//        return userRepository.save(user);
//    }
//
//    public Users authenticate(UserDto input) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getEmail(),
//                        input.getPassword()
//                )
//        );
//
//        return userRepository.findByEmail(input.getEmail())
//                .orElseThrow();
//    }
//}