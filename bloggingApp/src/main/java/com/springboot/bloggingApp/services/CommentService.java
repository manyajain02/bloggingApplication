package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Long postId);
    void deleteComment(Long commentId);
}