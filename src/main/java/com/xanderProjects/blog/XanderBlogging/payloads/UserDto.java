package com.xanderProjects.blog.XanderBlogging.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters !!")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Kindly provide Valid Email")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 3, max = 15, message = "Password must be min of 3 chars and max of 10 chars !!")
    private String password;

    @NotEmpty(message = "Description is required")
    private String about;
}