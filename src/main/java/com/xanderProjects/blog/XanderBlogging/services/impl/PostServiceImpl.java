package com.xanderProjects.blog.XanderBlogging.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xanderProjects.blog.XanderBlogging.entities.Category;
import com.xanderProjects.blog.XanderBlogging.entities.Post;
import com.xanderProjects.blog.XanderBlogging.entities.User;
import com.xanderProjects.blog.XanderBlogging.exceptions.ResourceNotFoundException;
import com.xanderProjects.blog.XanderBlogging.payloads.PostDto;
import com.xanderProjects.blog.XanderBlogging.payloads.PostResponse;
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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post receivedPost = this.postRepo.findById(postId)
                            .orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        receivedPost.setTitle(postDto.getTitle());
        receivedPost.setContent(postDto.getContent());
        receivedPost.setImageName(postDto.getImageName());
        this.postRepo.save(receivedPost);

        PostDto updatedPost = this.modelMapper.map(receivedPost, PostDto.class);
        return updatedPost;
    }

    @Override
    public void deletePost(Integer postId) {
        Post receivedPost = this.postRepo.findById(postId)
                            .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));

        this.postRepo.delete(receivedPost);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePosts = this.postRepo.findAll(p);
        List<Post> allPosts = pagePosts.getContent();
        List<PostDto> allPostDto = allPosts.stream()
                                    .map(post -> this.modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPostDto);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post receivedPost = this.postRepo.findById(postId)
                            .orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
        return this.modelMapper.map(receivedPost, PostDto.class);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
        Category category = this.categoryRepo.findById(categoryId)
                            .orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));

        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePosts = this.postRepo.findByCategory(category, p);

        List<Post> receivedPosts = pagePosts.getContent();
        List<PostDto> recePostDtos = receivedPosts.stream()
                                    .map(post -> this.modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
                                
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(recePostDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        
        Pageable p = PageRequest.of(pageNumber, pageSize);
        Page<Post> pagePosts = this.postRepo.findByUser(user, p);

        List<Post> receivedPosts= pagePosts.getContent();
        List<PostDto> recePostDtos = receivedPosts.stream()
                                    .map(post -> this.modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(recePostDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
       
        List<Post> foundPost = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> foundPostDto = foundPost.stream()
                                    .map(post -> this.modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return foundPostDto;
    }

}
