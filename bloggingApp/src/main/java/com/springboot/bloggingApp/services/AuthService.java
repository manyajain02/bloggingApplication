package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.payloads.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto Signup(UserDto userDto);

    String refreshToken(String refreshToken);
}
