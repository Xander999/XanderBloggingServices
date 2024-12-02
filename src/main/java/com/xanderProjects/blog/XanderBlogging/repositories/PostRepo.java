package com.xanderProjects.blog.XanderBlogging.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xanderProjects.blog.XanderBlogging.entities.Category;
import com.xanderProjects.blog.XanderBlogging.entities.Post;
import com.xanderProjects.blog.XanderBlogging.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    // List<Post> findByUser(User user);
    // List<Post> findByCategory(Category category, Pageable pageable);
    // List<Post> findByTitleContaining(String title, Pageable pageable);

    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);
    List<Post> findByTitleContaining(String title);

}
