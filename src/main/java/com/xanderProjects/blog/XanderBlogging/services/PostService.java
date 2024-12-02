package com.xanderProjects.blog.XanderBlogging.services;

import java.util.List;

import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;
import com.xanderProjects.blog.XanderBlogging.payloads.PostResponse;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update

    PostDto updatePost(PostDto postDto, Integer postId);

    //delete

    void deletePost(Integer postId);

    // get all post

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // get single post

    PostDto getPostById(Integer postId);

    //get all post by Category

    PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

    //get all post by user

    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);

    // search post by keyword

    List<PostDto> searchPosts(String keyword);


}
