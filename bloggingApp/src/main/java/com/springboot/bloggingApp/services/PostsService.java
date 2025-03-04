package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.payloads.PostDto;

import java.util.List;

public interface PostsService {

    PostDto createPost(PostDto post,Long userId, Long CategoryId);

    PostDto updatePost(PostDto post, Long postId);

    void deletePost(Long postId);

    PostDto getPostById(Long PostId);

    List<PostDto> getAllPosts();

    List<PostDto> getPostsByCategory(Long categoryId);

    List<PostDto> getPostsByUser(Long userId);

    PostDto serchPost(String keyword);
}
