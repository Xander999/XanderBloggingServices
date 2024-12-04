package com.xanderProjects.blog.XanderBlogging.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xanderProjects.blog.XanderBlogging.payloads.ApiResponse;
import com.xanderProjects.blog.XanderBlogging.payloads.CategoryDto;
import com.xanderProjects.blog.XanderBlogging.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/category")
public class CategoryControllers {

    @Autowired
    private CategoryService categoryServiceImpl;

    //create Category
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryServiceImpl.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    //update Category
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(
    @Valid @RequestBody CategoryDto categoryDto,    
    @PathVariable Integer id) {
        CategoryDto updatedCategory = this.categoryServiceImpl.updateCategory(categoryDto, id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    //delete Category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        this.categoryServiceImpl.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
    }


    //get all Categories
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> allCategories = this.categoryServiceImpl.getAllCategory();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    //get category by ids
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getMethodName(@PathVariable Integer id) {
        CategoryDto category = this.categoryServiceImpl.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.FOUND);
    }
    
}
