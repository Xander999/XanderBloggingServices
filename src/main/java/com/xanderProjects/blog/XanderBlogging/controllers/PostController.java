package com.xanderProjects.blog.XanderBlogging.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xanderProjects.blog.XanderBlogging.config.AppContants;
import com.xanderProjects.blog.XanderBlogging.payloads.ApiResponse;
import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;
import com.xanderProjects.blog.XanderBlogging.payloads.PostResponse;
import com.xanderProjects.blog.XanderBlogging.services.FileService;
import com.xanderProjects.blog.XanderBlogging.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

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
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    //create
    @PostMapping("/create/{userId}/{categoryId}")
    public ResponseEntity<PostDto> createPost(
    @RequestBody PostDto postDto,
    @PathVariable Integer userId,
    @PathVariable Integer categoryId
    ){
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    // get
    @GetMapping("/")
    public ResponseEntity<PostResponse> getallPost(
        @RequestParam(value="pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(value="pageSize", defaultValue=AppContants.PAGE_SIZE, required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppContants.SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppContants.SORT_DIRECTION, required = false ) String sortDir
    ) {
        PostResponse allPostResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allPostResponse, HttpStatus.OK);
    }

    // get by Id
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto receivedPost = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(receivedPost, HttpStatus.FOUND);
    }

    //get by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PostResponse> getPostByCategory(
        @PathVariable Integer categoryId,
        @RequestParam(value="pageNumber", defaultValue=AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize
        ) {

        PostResponse receivedPostsResponse = this.postService.getPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(receivedPostsResponse, HttpStatus.FOUND);
    }

    //get by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<PostResponse> getPostByUser(
        @PathVariable Integer userId,
        @RequestParam(value="pageNumber", defaultValue=AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize
        ) {
            PostResponse receivedPostsResponse = this.postService.getPostsByUser(userId, pageNumber, pageSize);

        return new ResponseEntity<>(receivedPostsResponse, HttpStatus.FOUND);
    }

    // delete post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
    }

    //update post
    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePostDetails(
        @RequestBody PostDto postDto,    
        @PathVariable Integer postId) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    //search post
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> getPostByKeyword(@PathVariable String keyword) {
        List<PostDto> foundPost = this.postService.searchPosts(keyword);

        return new ResponseEntity<>(foundPost, HttpStatus.FOUND);
    }
    
    //post Image upload
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
        @RequestParam("image") MultipartFile image,
        @PathVariable Integer postId
    ) throws IOException{

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }
    // method to serve files
    @GetMapping(value = "/image/get/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImahe(
    @PathVariable("imageName") String imageName,
    HttpServletResponse response    
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
    

}
