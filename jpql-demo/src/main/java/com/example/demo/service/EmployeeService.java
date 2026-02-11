package com.example.demo.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> derived(double minSalary) {
        return repo.findBySalaryGreaterThanEqualOrderBySalaryDesc(minSalary);
    }

    public List<Employee> jpql(double minSalary) {
        return repo.findBySalaryJPQL(minSalary);
    }

    public List<Employee> nativeSql(double minSalary) {
        return repo.findBySalaryNative(minSalary);
    }
}