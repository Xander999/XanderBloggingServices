package com.xanderProjects.blog.XanderBlogging.services;

import java.util.List;

import com.xanderProjects.blog.XanderBlogging.entities.Post;
import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update

    Post updatePost(PostDto postDto);

    //delete

    void deletePost(Integer postId);

    // get all post

    List<Post> getAllPost();

    // get single post

    Post getPostById(Integer postId);

    //get all post by Category

    List<Post> getPostsByCategory(Integer categoryId);

    //get all post by user

    List<Post> getPostsByUser(Integer userId);

    // search post by keyword

    List<Post> searchPosts(String keyword);


}
