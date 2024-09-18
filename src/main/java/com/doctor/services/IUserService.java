package com.doctor.services;

import com.doctor.entities.Users;
import com.doctor.exception.BusinessException;
import com.doctor.requestDto.UserRequestDto;
import com.doctor.responseDto.UserResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IUserService {

    List<Users> allUsers();

//*********************************************************************

    UserResponseDto saveUser(@Valid UserRequestDto userRequestDto);

    Object updateUser(@Valid UserRequestDto userRequestDto, Long userId) throws BusinessException;

    String deleteUser(Long userId) throws BusinessException;

    UserResponseDto getUserByUserId(Long userId) throws BusinessException;

    List<UserResponseDto> getAllUsers();
}
