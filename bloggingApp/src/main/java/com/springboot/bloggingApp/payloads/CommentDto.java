package com.springboot.bloggingApp.payloads;

import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int id;

    private String content;

//    @ManyToOne
//    private User user;
}
