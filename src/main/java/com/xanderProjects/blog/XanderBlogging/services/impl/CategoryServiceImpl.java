package com.xanderProjects.blog.XanderBlogging.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanderProjects.blog.XanderBlogging.entities.Category;
import com.xanderProjects.blog.XanderBlogging.exceptions.ResourceNotFoundException;
import com.xanderProjects.blog.XanderBlogging.payloads.CategoryDto;
import com.xanderProjects.blog.XanderBlogging.repositories.CategoryRepo;
import com.xanderProjects.blog.XanderBlogging.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        
        Category category = this.DtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepo.save(category);
        return this.CategoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                    .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory = this.categoryRepo.save(category);

        return this.CategoryToDto(savedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                            .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream()
                                        .map(category -> this.CategoryToDto(category))
                                        .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                            .orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        return this.CategoryToDto(category);
    }


    //------------------------------ Utilities Methods

    public CategoryDto CategoryToDto(Category category){
        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    public Category DtoToCategory(CategoryDto categoryDto){
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return category;
    }


}
