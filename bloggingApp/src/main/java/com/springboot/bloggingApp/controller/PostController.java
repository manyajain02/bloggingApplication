package com.springboot.bloggingApp.controller;

import com.springboot.bloggingApp.config.AppConstant;
import com.springboot.bloggingApp.payloads.ApiResponse;
import com.springboot.bloggingApp.payloads.PostDto;
import com.springboot.bloggingApp.payloads.PostResponse;
import com.springboot.bloggingApp.services.FileService;
import com.springboot.bloggingApp.services.PostsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Long userId,
                                              @PathVariable Long categoryId) {

        PostDto createdPost = postsService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long userId) {

        List<PostDto> getPosts = postsService.getPostsByUser(userId);

        return new ResponseEntity<List<PostDto>>(getPosts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId) {

        List<PostDto> getPosts = postsService.getPostsByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(getPosts, HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<PostDto> getPostsId(@PathVariable Long postId) {

        PostDto getPost = postsService.getPostById(postId);

        return new ResponseEntity<PostDto>(getPost, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = "sorBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {

        PostResponse postResponse = postsService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updateUser(@RequestBody PostDto postDto,
                                              @PathVariable(name = "postId") Long postId) {
        PostDto updatedUser = postsService.updatePost(postDto, postId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "postId") Long postId) {
        postsService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String keyword) {

        List<PostDto> getPosts = postsService.searchPosts(keyword);

        return new ResponseEntity<List<PostDto>>(getPosts, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam(value = "image") MultipartFile image,
                                                   @PathVariable Long postId) throws IOException {
        String filename = fileService.uploadImage(path, image);
        PostDto postDto = postsService.getPostById(postId);
        postDto.setAddImage(filename);
        PostDto updatedPosts = postsService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPosts, HttpStatus.OK);
    }
    // method to serve files
    @GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
    }
