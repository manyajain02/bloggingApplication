package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.entity.User;
import com.springboot.bloggingApp.exceptions.ResourceNotFoundException;
import com.springboot.bloggingApp.payloads.UserDto;
import com.springboot.bloggingApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User ", "User Not Found with id" ,id));
    }
}

