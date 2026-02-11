package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DepartmentCreateRequest;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

//    // Create a department with 3 employees
//    @PostMapping("/create")
//    public Department create() {
//        return service.createSampleDepartment();
//    }
    
    @PostMapping
    public Department create(@RequestBody DepartmentCreateRequest req) {
        return service.createDepartment(req);
    }

    // Fetch all
    @GetMapping
    public List<Department> all() {
        return service.getAllDepartments();
    }

    // Fetch by id
    @GetMapping("/{id}")
    public Department byId(@PathVariable Long id) {
        return service.getDepartmentById(id);
    }

    // Delete by id
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteDepartment(id);
        return "Deleted department id = " + id;
    }
}