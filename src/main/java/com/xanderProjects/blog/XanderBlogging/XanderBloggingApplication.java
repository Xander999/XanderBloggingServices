package com.xanderProjects.blog.XanderBlogging;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class XanderBloggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(XanderBloggingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMappper(){
		return new ModelMapper();
	}

}
