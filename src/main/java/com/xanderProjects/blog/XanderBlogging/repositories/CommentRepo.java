package com.xanderProjects.blog.XanderBlogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xanderProjects.blog.XanderBlogging.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
