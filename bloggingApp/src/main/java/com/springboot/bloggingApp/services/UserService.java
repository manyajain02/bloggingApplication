package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,Long userId);

    void deleteUser(Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

}
