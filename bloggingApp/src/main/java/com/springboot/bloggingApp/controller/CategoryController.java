package com.springboot.bloggingApp.controller;

import com.springboot.bloggingApp.payloads.ApiResponse;
import com.springboot.bloggingApp.payloads.CategoryDto;
import com.springboot.bloggingApp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategories(@Valid @RequestBody CategoryDto categoryDto) {
       CategoryDto createCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategories(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
        CategoryDto updateCategory = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategories(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        ApiResponse apiResponse = new ApiResponse("Category is successfully Deleted", true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
        CategoryDto category = categoryService.getCategory(categoryId);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        List<CategoryDto> category = categoryService.getAllCategories();
        return ResponseEntity.ok(category);
    }
}
