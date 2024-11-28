package com.xanderProjects.blog.XanderBlogging.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanderProjects.blog.XanderBlogging.entities.Category;
import com.xanderProjects.blog.XanderBlogging.entities.Post;
import com.xanderProjects.blog.XanderBlogging.entities.User;
import com.xanderProjects.blog.XanderBlogging.exceptions.ResourceNotFoundException;
import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;
import com.xanderProjects.blog.XanderBlogging.repositories.CategoryRepo;
import com.xanderProjects.blog.XanderBlogging.repositories.PostRepo;
import com.xanderProjects.blog.XanderBlogging.repositories.UserRepo;
import com.xanderProjects.blog.XanderBlogging.services.PostService;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId)
            .orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
        
        Category category = this.categoryRepo.findById(categoryId)
            .orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date()); 
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public List<PostDto> getAllPost() {
       List<Post> allPosts = this.postRepo.findAll();
       List<PostDto> allPostDto = allPosts.stream()
                                    .map(post -> this.modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return allPostDto;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post receivedPost = this.postRepo.findById(postId)
                            .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
        return this.modelMapper.map(receivedPost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                            .orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));

        List<Post> receivedPosts = this.postRepo.findByCategory(category);

        List<PostDto> recePostDtos = receivedPosts.stream()
                                    .map(post -> this.modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return recePostDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        List<Post> receivedPosts = this.postRepo.findByUser(user);
        List<PostDto> recePostDtos = receivedPosts.stream()
                                    .map(post -> this.modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return recePostDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
