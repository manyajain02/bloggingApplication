package com.springboot.bloggingApp.payloads;

import com.springboot.bloggingApp.entity.Category;
import com.springboot.bloggingApp.entity.Comment;
import com.springboot.bloggingApp.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PostDto {
    private Long postId;

    private String title;

    private String content;

    private String addImage;

    private Date addDate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();

}
