package com.springboot.bloggingApp.services.impl;

import com.springboot.bloggingApp.entity.Comment;
import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.exceptions.ResourceNotFoundException;
import com.springboot.bloggingApp.payloads.CommentDto;
import com.springboot.bloggingApp.repository.CommentRepository;
import com.springboot.bloggingApp.repository.PostsRepository;
import com.springboot.bloggingApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = this.postsRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        Comment comment  = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment =  commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {

        Comment comment  = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment id", commentId));

        commentRepository.delete(comment);
    }
}