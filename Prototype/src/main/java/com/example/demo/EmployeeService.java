package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service

@Scope("prototype")



public class EmployeeService {

    public EmployeeService() {
        System.out.println("EmployeeService Constructor Called: NEW Bean Created");
    }

    public String getEmployeeInfo() {
        return "Employee Service - Prototype Bean";
    }
}

