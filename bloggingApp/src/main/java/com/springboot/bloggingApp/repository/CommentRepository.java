package com.springboot.bloggingApp.repository;

import com.springboot.bloggingApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
