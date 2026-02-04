package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PrototypeApplication {

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(PrototypeApplication.class, args);

        
        EmployeeService obj1 = context.getBean(EmployeeService.class);
        EmployeeService obj2 = context.getBean(EmployeeService.class);

        System.out.println(obj1.getEmployeeInfo());

        
        System.out.println("obj1 hashCode: " + obj1.hashCode());
        System.out.println("obj2 hashCode: " + obj2.hashCode());

        if (obj1 == obj2) {
            System.out.println("SAME OBJECT => Not Prototype");
        } else {
            System.out.println("DIFFERENT OBJECTS => Prototype Scope Confirmed!");
        }
    }
}
