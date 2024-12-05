package com.xanderProjects.blog.XanderBlogging.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xanderProjects.blog.XanderBlogging.payloads.ApiResponse;
import com.xanderProjects.blog.XanderBlogging.payloads.UserDto;
import com.xanderProjects.blog.XanderBlogging.services.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //Update user
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(
        @Valid @RequestBody UserDto userDto,
        @PathVariable Integer id){
        UserDto updatedUser = this.userService.update(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //Get User By Id
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        UserDto getUser = this.userService.getUserById(id);
        return new ResponseEntity<>(getUser, HttpStatus.FOUND);
    }

    // Get All Users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> users = this.userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
        
    }

    // Delete All Users
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }


    //Show Logged in User
    @GetMapping("/loggedIn/user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }
    

}
