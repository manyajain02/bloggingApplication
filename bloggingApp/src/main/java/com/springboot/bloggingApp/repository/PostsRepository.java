package com.springboot.bloggingApp.repository;

import com.springboot.bloggingApp.entity.Category;
import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
