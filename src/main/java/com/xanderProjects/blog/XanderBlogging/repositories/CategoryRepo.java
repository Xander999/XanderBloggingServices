package com.xanderProjects.blog.XanderBlogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xanderProjects.blog.XanderBlogging.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
