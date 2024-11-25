package com.xanderProjects.blog.XanderBlogging.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;
import com.xanderProjects.blog.XanderBlogging.services.impl.PostServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postServiceImpl;

    //create
    @PostMapping("/create/{userId}/{categoryId}")
    public ResponseEntity<PostDto> createPost(
    @RequestBody PostDto postDto,
    @PathVariable Integer userId,
    @PathVariable Integer categoryId
    ){
        PostDto createdPost = this.postServiceImpl.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

}
