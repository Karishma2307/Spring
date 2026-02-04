package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class School {	//Spring injects the Student bean

    @Autowired
    private Student student;

    public void start() {
        student.study();
    }
}
