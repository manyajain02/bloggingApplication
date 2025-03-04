package com.springboot.bloggingApp.payloads;

import com.springboot.bloggingApp.entity.Category;
import com.springboot.bloggingApp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDto {

    private String title;

    private String content;

    private String addImage;

    private Date addDate;

    private CategoryDto category;

    private UserDto user;

}
