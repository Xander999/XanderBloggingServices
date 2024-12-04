package com.xanderProjects.blog.XanderBlogging.payloads;

import com.xanderProjects.blog.XanderBlogging.entities.Post;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CommentDto {

    private int id;

    private String content;

}
