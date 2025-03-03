package com.springboot.bloggingApp.services.impl;

import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.payloads.PostsDto;
import com.springboot.bloggingApp.repository.PostsRepository;
import com.springboot.bloggingApp.services.PostsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Post createPost(PostsDto post) {

        return null;
    }

    @Override
    public Post updatePost(PostsDto post) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {

    }

    @Override
    public Post getPostById(Long PostId) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return List.of();
    }

    @Override
    public List<Post> getPostsByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public List<Post> getPostsByUser(Long userId) {
        return List.of();
    }

    @Override
    public Post serchPost(String keyword) {
        return null;
    }
}
