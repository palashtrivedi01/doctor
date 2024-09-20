package com.doctor.serviceImpl;

import com.doctor.ENUM.Role;
import com.doctor.entities.Users;
import com.doctor.exception.BusinessException;
import com.doctor.exception.ControllerException;
import com.doctor.repositories.IUserRepository;
import com.doctor.requestDto.UserRequestDto;
import com.doctor.responseDto.UserResponseDto;
import com.doctor.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Users> allUsers() {
        List<Users> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }


//**************************************************************************


    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        Users user = new Users();

        user.setEmail(userRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequestDto.getPassword()));
        user.setMobile(userRequestDto.getMobile());
//        user.setRole(Role.valueOf(userRequestDto.getRole()));

        user.setRole(userRequestDto.getRole());
        user.setName(userRequestDto.getName());

        Users savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(userRequestDto.getPassword());
        userResponseDto.setMobile(savedUser.getMobile());
        userResponseDto.setRole(savedUser.getRole().toString());


        return userResponseDto;

    }

    @Override
    public Object updateUser(UserRequestDto userRequestDto, Long userId) throws BusinessException {

        Users user = userRepository.findById(userId).orElseThrow(
                () -> new BusinessException("User not found with given user id : " + userId)
        );

        user.setEmail(userRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        user.setMobile(userRequestDto.getMobile());
//        user.setRole(Role.valueOf(userRequestDto.getRole()));
        user.setRole(userRequestDto.getRole());

            Users updatedUser = userRepository.save(user);

            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setEmail(updatedUser.getEmail());
            userResponseDto.setPassword(userRequestDto.getPassword());
            userResponseDto.setMobile(updatedUser.getMobile());
            userResponseDto.setRole(updatedUser.getRole().toString());

            return "User successfully updated, check updated user details : " + "\n"
                    + "Updated email : " + userResponseDto.getEmail() + "\n"
                    + "Updated password : " + userResponseDto.getPassword() + "\n"
                    + "Updated mobile number : " + userResponseDto.getMobile() + "\n"
                    + "Updated role : " + userResponseDto.getRole();

    }

    @Override
    public String deleteUser(Long userId) throws BusinessException {
        Optional<Users> user = this.userRepository.findById(userId);
        if(user.isPresent()) {
            Users userToDelete = user.get();
            this.userRepository.delete(userToDelete);
            return "User successfully deleted";
        }
        else
            throw new BusinessException("User not found with given user id : " + userId);
    }

    @Override
    public UserResponseDto getUserByUserId(Long userId) throws BusinessException {
       Users user = this.userRepository.findById(userId).orElseThrow(
               () -> new BusinessException("User not found with given user id : " + userId)
       );

       UserResponseDto userResponseDto = new UserResponseDto();
       userResponseDto.setEmail(user.getEmail());
       userResponseDto.setMobile(user.getMobile());
       userResponseDto.setRole(user.getRole().toString());
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        List<Users> users = this.userRepository.findAll();

        List<UserResponseDto> userResponseDtos = users.stream().map(usersInList -> {

            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setEmail(usersInList.getEmail());
            userResponseDto.setPassword("Password is hidden due to security reasons!");
            userResponseDto.setMobile(usersInList.getMobile());
            userResponseDto.setRole(String.valueOf(usersInList.getRole()));

            return userResponseDto;

        }).toList();

        return userResponseDtos;
    }


}
