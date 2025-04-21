package com.springboot.bloggingApp.payloads;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RoleDto {
    @Id
    private int id;

    private String name;
}
