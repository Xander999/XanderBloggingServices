package com.xanderProjects.blog.XanderBlogging.services.impl;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanderProjects.blog.XanderBlogging.entities.Comment;
import com.xanderProjects.blog.XanderBlogging.entities.Post;
import com.xanderProjects.blog.XanderBlogging.exceptions.ResourceNotFoundException;
import com.xanderProjects.blog.XanderBlogging.payloads.CommentDto;
import com.xanderProjects.blog.XanderBlogging.repositories.CommentRepo;
import com.xanderProjects.blog.XanderBlogging.repositories.PostRepo;
import com.xanderProjects.blog.XanderBlogging.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId)
                            .orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment Id", commentId));
        this.commentRepo.delete(comment);
    }

}
