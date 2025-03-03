package com.springboot.bloggingApp.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Long CategoryId;

    @NotBlank
    @Size(min= 4)
    private String categoryTitle;

    @NotBlank
    @Size(min = 5)
    private String categoryDescription;
}
