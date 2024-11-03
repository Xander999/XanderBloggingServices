package com.xanderProjects.blog.XanderBlogging.services;

import java.util.List;
import com.xanderProjects.blog.XanderBlogging.payloads.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto update(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
} 

