package com.xanderProjects.blog.XanderBlogging.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xanderProjects.blog.XanderBlogging.entities.User;
import com.xanderProjects.blog.XanderBlogging.payloads.UserDto;
import com.xanderProjects.blog.XanderBlogging.repositories.UserRepo;
import com.xanderProjects.blog.XanderBlogging.services.UserService;
import com.xanderProjects.blog.XanderBlogging.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User userSaved = this.userRepo.save(user);
        return this.userToDto(userSaved);
    }

    @Override
    public UserDto update(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User","User Id ",userId));
        // user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User userSaved = this.userRepo.save(user);
        return this.userToDto(userSaved);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> usersDto = users.stream()
        .map(user -> this.userToDto(user))
        .collect(Collectors.toList());
        
        return usersDto;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){

        User user = this.modelMapper.map(userDto, User.class);
        // user.setId(userDto.getId());
        // user.setEmail(userDto.getEmail());
        // user.setName(userDto.getName());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());

        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        // UserDto userDto = new UserDto();
        // userDto.setId(user.getId());
        // userDto.setEmail((user.getEmail()));
        // userDto.setName(user.getName());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getAbout());

        return userDto;
    }
}
