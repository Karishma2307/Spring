package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Student { //this makes it a Spring Bean

    public void study() {
        System.out.println("Student is studying");
    }
}
