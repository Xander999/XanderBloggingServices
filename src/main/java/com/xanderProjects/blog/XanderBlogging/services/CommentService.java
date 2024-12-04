package com.xanderProjects.blog.XanderBlogging.services;

import com.xanderProjects.blog.XanderBlogging.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}
