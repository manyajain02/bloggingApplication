package com.springboot.bloggingApp.services.impl;

import com.springboot.bloggingApp.entity.Category;
import com.springboot.bloggingApp.exceptions.ResourceNotFoundException;
import com.springboot.bloggingApp.payloads.CategoryDto;
import com.springboot.bloggingApp.repository.CategoryRepository;
import com.springboot.bloggingApp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category exists = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","category Id ", categoryId));
        exists.setCategoryTitle(categoryDto.getCategoryTitle());
        exists.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory = categoryRepository.save(exists);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category exists = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","category Id ", categoryId));
        categoryRepository.delete(exists);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","category Id ", categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoLst=  categoryList.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoLst;
    }
}
