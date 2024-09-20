package com.doctor.restcontrollers;

import com.doctor.entities.Users;
import com.doctor.exception.BusinessException;
import com.doctor.requestDto.UserRequestDto;
import com.doctor.responseDto.UserResponseDto;
import com.doctor.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/hms/user")
public class UserRestController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/saveUser")
    public ResponseEntity<UserResponseDto> saveUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return new ResponseEntity<UserResponseDto>(this.iUserService.saveUser(userRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable("userId") Long userId) throws BusinessException {
        return new ResponseEntity<>(this.iUserService.updateUser(userRequestDto, userId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) throws BusinessException{
        return ResponseEntity.ok(this.iUserService.deleteUser(userId));
    }

    @GetMapping("/getUserByUserId/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) throws BusinessException {
        return ResponseEntity.ok(this.iUserService.getUserByUserId(userId));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(this.iUserService.getAllUsers());
    }


//*******************************************************************************

    @GetMapping("/me")
    public ResponseEntity<Users> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users currentUser = (Users) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<Users>> allUsers() {
        List <Users> users = iUserService.allUsers();
        return ResponseEntity.ok(users);
    }


}
