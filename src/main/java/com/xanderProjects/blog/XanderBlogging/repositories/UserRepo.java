package com.xanderProjects.blog.XanderBlogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xanderProjects.blog.XanderBlogging.entities.User;


// JPA Repositirues gives us power to handle database such as findAll, findById, Paginations, delete .
// Any Database opertaions can be performed by JPARepositories class 

public interface UserRepo extends JpaRepository<User, Integer> {

}
