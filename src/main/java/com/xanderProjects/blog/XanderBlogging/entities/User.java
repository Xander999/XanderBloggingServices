package com.xanderProjects.blog.XanderBlogging.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @Column(name="user_name", nullable=false, length = 200)
        private String name;

        @Column(name="user_email", nullable=false)
        private String email;

        @Column(name="user_password", nullable = false)
        private String password;

        @Column(name = "user_info", nullable = true)
        private String about;


}