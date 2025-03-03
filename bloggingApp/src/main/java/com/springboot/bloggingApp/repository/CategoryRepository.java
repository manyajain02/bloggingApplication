package com.springboot.bloggingApp.repository;

import com.springboot.bloggingApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
