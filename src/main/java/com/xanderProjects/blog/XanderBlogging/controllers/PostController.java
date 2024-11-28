package com.xanderProjects.blog.XanderBlogging.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    // get
    @GetMapping("/")
    public ResponseEntity<List<PostDto>> getallPost() {
        List<PostDto> allPosts = this.postServiceImpl.getAllPost();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    // get by Id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto receivedPost = this.postServiceImpl.getPostById(postId);
        return new ResponseEntity<PostDto>(receivedPost, HttpStatus.FOUND);
    }

    //get by category
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> receivedPosts = this.postServiceImpl.getPostsByCategory(categoryId);

        return new ResponseEntity<>(receivedPosts, HttpStatus.FOUND);
    }

    //get by user
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> receivedPosts = this.postServiceImpl.getPostsByUser(userId);

        return new ResponseEntity<>(receivedPosts, HttpStatus.FOUND);
    }
    
    
    

}
