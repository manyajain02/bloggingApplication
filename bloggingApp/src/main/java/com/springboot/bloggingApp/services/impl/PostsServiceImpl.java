package com.springboot.bloggingApp.services.impl;

import com.springboot.bloggingApp.entity.Category;
import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.entity.User;
import com.springboot.bloggingApp.exceptions.ResourceNotFoundException;
import com.springboot.bloggingApp.payloads.PostDto;
import com.springboot.bloggingApp.repository.CategoryRepository;
import com.springboot.bloggingApp.repository.PostsRepository;
import com.springboot.bloggingApp.repository.UserRepository;
import com.springboot.bloggingApp.services.PostsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto,Long userId, Long categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User", "User Id",userId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        Post post = modelMapper.map(postDto, Post.class);

        post.setAddDate(new Date());
        post.setAddImage("default.png");
        post.setCategory(category);
        post.setUser(user);
        Post newPost =  postsRepository.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto post) {
        return null;
    }

    @Override
    public void deletePost(Long postId) {

    }

    @Override
    public PostDto getPostById(Long PostId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postsRepository.findAll();
        return posts
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","category Id ", categoryId));
        List<Post> posts =  postsRepository.findByCategory(category);
        List<PostDto> postDto = posts
                .stream()
                .map(post->modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
      return postDto;
    }

    @Override
    public List<PostDto> getPostsByUser(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        List<Post> users =  postsRepository.findAllByUser(user);
        return users
                .stream()
                .map(post->modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto serchPost(String keyword) {
        return null;
    }
}
