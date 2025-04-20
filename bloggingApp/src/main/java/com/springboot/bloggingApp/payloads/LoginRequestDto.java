package com.springboot.bloggingApp.payloads;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;
}
