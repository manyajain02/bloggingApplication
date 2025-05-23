package com.springboot.bloggingApp.services.impl;

import com.springboot.bloggingApp.entity.Category;
import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.entity.User;
import com.springboot.bloggingApp.exceptions.ResourceNotFoundException;
import com.springboot.bloggingApp.payloads.PostDto;
import com.springboot.bloggingApp.payloads.PostResponse;
import com.springboot.bloggingApp.repository.CategoryRepository;
import com.springboot.bloggingApp.repository.PostsRepository;
import com.springboot.bloggingApp.repository.UserRepository;
import com.springboot.bloggingApp.services.PostsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostDto updatePost(PostDto postDto,Long postId) {

        Post post =  postsRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id ", postId));

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setAddImage(postDto.getAddImage());
        Post updatedPost = postsRepository.save(post);

        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(Long postId) {

        Post post =  postsRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id ", postId));
        postsRepository.delete(post);
    }

    @Override
    public PostDto getPostById(Long postId) {

        Post post =  postsRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id ", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePosts = postsRepository.findAll(page);
        List<Post> allPosts = pagePosts.getContent();

        PostResponse postResponse = new PostResponse();
        List<PostDto> postDtos = allPosts
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
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
    public List<PostDto> searchPosts(String keyword) {
       List<Post> post =  postsRepository.findByTitleContaining(keyword);
     List<PostDto>  postDtos = post.stream()
               .map(post1 -> modelMapper.map(post1,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
