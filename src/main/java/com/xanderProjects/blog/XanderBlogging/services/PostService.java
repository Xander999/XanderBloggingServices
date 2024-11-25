package com.xanderProjects.blog.XanderBlogging.services;

import java.util.List;

import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update

    PostDto updatePost(PostDto postDto);

    //delete

    void deletePost(Integer postId);

    // get all post

    List<PostDto> getAllPost();

    // get single post

    PostDto getPostById(Integer postId);

    //get all post by Category

    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user

    List<PostDto> getPostsByUser(Integer userId);

    // search post by keyword

    List<PostDto> searchPosts(String keyword);


}
