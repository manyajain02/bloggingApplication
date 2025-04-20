package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.payloads.SignupDto;
import com.springboot.bloggingApp.payloads.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto Signup(SignupDto signupDto);

//    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
