package com.springboot.bloggingApp.services;

import com.springboot.bloggingApp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public  CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    public void deleteCategory(Long categoryId);

    public CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();
}
