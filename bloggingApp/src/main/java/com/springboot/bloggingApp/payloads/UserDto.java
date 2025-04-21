package com.springboot.bloggingApp.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.bloggingApp.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty()
    @Size(min=4, message = "User must be of minimum 4 character !!")
    private String name;

    @Email(message = "Email address must be valid !!")
    private String email;

//    @NotEmpty
    @Size(min = 3, max =  12, message = "Password must be minimum of 3 and maximum of 12 Characters")
    private String password;

    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
