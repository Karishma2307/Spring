package com.example.demo;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class LifeCycleService {

    
    public LifeCycleService() {
        System.out.println("Constructor: LifeCycleService bean created");
    }

  
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct: Bean initialization logic executed");
    }

   
    public String getMessage() {
        return "Bean is ready and being used!";
    }

   
    @PreDestroy
    public void destroy() {
        System.out.println("@PreDestroy: Bean destruction logic executed");
    }
}



