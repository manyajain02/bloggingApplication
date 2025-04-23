package com.springboot.bloggingApp.services.impl;

import com.springboot.bloggingApp.entity.User;
import com.springboot.bloggingApp.exceptions.ResourceNotFoundException;
import com.springboot.bloggingApp.exceptions.RuntimeConflictException;
import com.springboot.bloggingApp.payloads.UserDto;
import com.springboot.bloggingApp.repository.UserRepository;
import com.springboot.bloggingApp.security.JwtService;
import com.springboot.bloggingApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserServiceImpl userService;

    @Override
    public String[] login(String email, String password) {
        String[] tokens = new String[2];
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            User user = (User) authentication.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            tokens[0] = accessToken;
            tokens[1] = refreshToken;
        } catch (Exception exception) {
            System.out.println("New Exception here in login" + exception);
        }
        return tokens;
    }

    @Override
    public UserDto Signup(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeConflictException("Cannot Signup, User already exists with email" + user.getEmail());
        }
        //        User mappedUser =  modelMapper.map(userDto,User.class);
//        mappedUser.setRoles(Set.of(Role.));
//        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
//        User savedUser = userRepository.save(mappedUser);
        //Creating user related entities
//        return modelMapper.map(savedUser, UserDto.class);
        return userService.registerNewUser(userDto);
    }


    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("RefreshToken", "User Id", userId));

        return jwtService.generateAccessToken(user);
    }
}
