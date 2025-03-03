package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.payloads.PostsDto;

import java.util.List;

public interface PostsService {

    Post createPost(PostsDto post);

    Post updatePost(PostsDto post);

    void deletePost(Long postId);

    Post getPostById(Long PostId);

    List<Post> getAllPosts();

    List<Post> getPostsByCategory(Long categoryId);

    List<Post> getPostsByUser(Long userId);

    Post serchPost(String keyword);
}
