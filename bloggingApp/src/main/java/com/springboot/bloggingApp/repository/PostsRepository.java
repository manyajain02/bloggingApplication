package com.springboot.bloggingApp.repository;

import com.springboot.bloggingApp.entity.Category;
import com.springboot.bloggingApp.entity.Post;
import com.springboot.bloggingApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post,Long> {

    public User findAllByUser(User user);
    Category findByCategory(Category category);
}
