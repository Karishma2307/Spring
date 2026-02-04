package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SingletonApplication {

	public static void main(String[] args) {
		ApplicationContext context=
		SpringApplication.run(SingletonApplication.class, args);
		MessageService obj1=context.getBean(MessageService.class);
		MessageService obj2=context.getBean(MessageService.class);
		System.out.println(obj1.getMessage());
		System.out.println("obj1 hashCode:"+ obj1.hashCode());
		System.out.println("obj2 hashCode:"+obj2.hashCode());
		
		
		
		
		if(obj1==obj2) {
			System.out.println("Same object=>Singleton Scope Confirmed");
		}else{
			System.out.println("Different objects =>Not Singleton");
			
		}
	}

}
