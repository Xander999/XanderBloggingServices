package com.xanderProjects.blog.XanderBlogging.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xanderProjects.blog.XanderBlogging.payloads.ApiResponse;
import com.xanderProjects.blog.XanderBlogging.payloads.CommentDto;
import com.xanderProjects.blog.XanderBlogging.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> createComment(
        @RequestBody CommentDto comment,
        @PathVariable Integer postId
        ){

            CommentDto createdComment = this.commentService.createComment(comment, postId);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>( new ApiResponse("Comment Deleted Sucessfully", true), HttpStatus.OK);
    }

}
