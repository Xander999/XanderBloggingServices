package com.xanderProjects.blog.XanderBlogging.services;

import java.util.List;

import com.xanderProjects.blog.XanderBlogging.payloads.CategoryDto;

 public interface CategoryService {

    // create
     CategoryDto createCategory(CategoryDto categoryDto);
    
    //update
     CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    
    //delete
     void deleteCategory(Integer categoryId);
    
    //getAll
     List<CategoryDto> getAllCategory();
    
    //getById
     CategoryDto getCategoryById(Integer categoryId);

}
