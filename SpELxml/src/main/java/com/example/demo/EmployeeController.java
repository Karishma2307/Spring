package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final Employee employee;

  
    public EmployeeController(Employee employee) {
        this.employee = employee;
    }

    @GetMapping("/employee")
    public String getEmployee() {
        return employee.getDetails();
    }
}
