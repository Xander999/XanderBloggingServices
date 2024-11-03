package com.xanderProjects.blog.XanderBlogging;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xanderProjects.blog.XanderBlogging.repositories.UserRepo;

@SpringBootTest
class XanderBloggingApplicationTests {


	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest(){

		String className = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();

		System.out.println("ClassName "+className);
		System.out.println("PackageName :"+packageName);
		
	}

}
