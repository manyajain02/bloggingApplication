package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.payloads.PostDto;
import com.springboot.bloggingApp.payloads.PostResponse;

import java.util.List;

public interface PostsService {

    PostDto createPost(PostDto post,Long userId, Long CategoryId);

    PostDto updatePost(PostDto post, Long postId);

    void deletePost(Long postId);

    PostDto getPostById(Long PostId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sorBy, String sortDir);

    List<PostDto> getPostsByCategory(Long categoryId);

    List<PostDto> getPostsByUser(Long userId);

   List<PostDto> searchPosts(String keyword);
}
