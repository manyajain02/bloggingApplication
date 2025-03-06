package com.springboot.bloggingApp.controller;

import com.springboot.bloggingApp.entity.Comment;
import com.springboot.bloggingApp.payloads.ApiResponse;
import com.springboot.bloggingApp.payloads.CommentDto;
import com.springboot.bloggingApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable Long postId) {
       CommentDto comment =  commentService.createComment(commentDto,postId);
       return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/post/{postId}/comments")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long postId) {

        commentService.deleteComment(postId);
        ApiResponse apiResponse = new ApiResponse("Comment Deleted Successfully ", true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}