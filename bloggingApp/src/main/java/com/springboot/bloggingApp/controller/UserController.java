package com.springboot.bloggingApp.controller;

import com.springboot.bloggingApp.payloads.ApiResponse;
import com.springboot.bloggingApp.payloads.UserDto;
import com.springboot.bloggingApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //  POST - CREATE user
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }
    // PUT - UPDATE User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable(name = "userId") Long userId) {
        UserDto updatedUser = userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable(name = "userId") Long userId) {
        UserDto user= userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
