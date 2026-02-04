package com.example.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class BeanApplication {    

	public static void main(String[] args) {
		ApplicationContext context=
		SpringApplication.run(BeanApplication.class, args);
	
		School school = context.getBean(School.class);
        school.start();
        }

}
