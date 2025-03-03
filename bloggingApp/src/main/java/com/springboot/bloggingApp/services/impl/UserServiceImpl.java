package com.springboot.bloggingApp.services.impl;

import com.springboot.bloggingApp.entity.User;
import com.springboot.bloggingApp.exceptions.ResourceNotFoundException;
import com.springboot.bloggingApp.payloads.UserDto;
import com.springboot.bloggingApp.repository.UserRepository;
import com.springboot.bloggingApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = maptoUser(userDto);
       User savedUser =  userRepository.save(user);
        return mapToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
       User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);
       return mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user->mapToUserDto(user)).collect(Collectors.toList());
    }

    public User maptoUser(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return modelMapper.map(userDto, User.class);
    }

    public UserDto mapToUserDto(User user) {

//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return  modelMapper.map(user, UserDto.class);
    }
}
