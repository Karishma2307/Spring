package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/derived/{minSalary}")
    public List<Employee> derived(@PathVariable double minSalary) {
        return service.derived(minSalary);
    }

    @GetMapping("/jpql/{minSalary}")
    public List<Employee> jpql(@PathVariable double minSalary) {
        return service.jpql(minSalary);
    }

    @GetMapping("/native/{minSalary}")
    public List<Employee> nativeSql(@PathVariable double minSalary) {
        return service.nativeSql(minSalary);
    }
}