package com.example.studentregistermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class StudentRegisterManagementApplication {

	public static void main(String[] args) {
		//ConfigurableApplicationContext context =SpringApplication.run(StudentRegisterManagementApplication.class, args);
		//Object dataSource = context.getBean("dataSource");
		//System.out.println(dataSource);
		SpringApplication.run(StudentRegisterManagementApplication.class, args);
	}

}
