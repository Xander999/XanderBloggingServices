package com.xanderProjects.blog.XanderBlogging.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xanderProjects.blog.XanderBlogging.payloads.ApiResponse;
import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;
import com.xanderProjects.blog.XanderBlogging.payloads.PostResponse;
import com.xanderProjects.blog.XanderBlogging.services.impl.PostServiceImpl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<PostResponse> getallPost(
        @RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value="pageSize", defaultValue="5", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc", required = false ) String sortDir
    ) {
        PostResponse allPostResponse = this.postServiceImpl.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allPostResponse, HttpStatus.OK);
    }

    // get by Id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto receivedPost = this.postServiceImpl.getPostById(postId);
        return new ResponseEntity<PostDto>(receivedPost, HttpStatus.FOUND);
    }

    //get by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategory(
        @PathVariable Integer categoryId,
        @RequestParam(value="pageNumber", defaultValue="0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
        ) {

        PostResponse receivedPostsResponse = this.postServiceImpl.getPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(receivedPostsResponse, HttpStatus.FOUND);
    }

    //get by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<PostResponse> getPostByUser(
        @PathVariable Integer userId,
        @RequestParam(value="pageNumber", defaultValue="0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
        ) {
            PostResponse receivedPostsResponse = this.postServiceImpl.getPostsByUser(userId, pageNumber, pageSize);

        return new ResponseEntity<>(receivedPostsResponse, HttpStatus.FOUND);
    }

    // delete post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postServiceImpl.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }

    //update post
    @PutMapping("update/{postId}")
    public ResponseEntity<PostDto> updatePostDetails(
        @RequestBody PostDto postDto,    
        @PathVariable Integer postId) {
        PostDto updatedPost = this.postServiceImpl.updatePost(postDto, postId);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //search post
    @GetMapping("search/{keyword}")
    public ResponseEntity<List<PostDto>> getPostByKeyword(@PathVariable String keyword) {
        List<PostDto> foundPost = this.postServiceImpl.searchPosts(keyword);

        return new ResponseEntity<>(foundPost, HttpStatus.FOUND);
    }
    
    
    
    

}
