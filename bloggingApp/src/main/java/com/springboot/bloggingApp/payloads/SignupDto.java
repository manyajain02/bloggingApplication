package com.springboot.bloggingApp.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupDto {

    private String name;

    private String email;

    private String password;
}
