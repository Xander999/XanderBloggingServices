package com.xanderProjects.blog.XanderBlogging.payloads;

import java.util.Date;

import com.xanderProjects.blog.XanderBlogging.entities.Category;
import com.xanderProjects.blog.XanderBlogging.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PostDto {

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private Category category;

    private User user;


}
