package com.example.demo.service;


import com.example.demo.entity.Department;
import com.example.demo.repo.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepo;

    public DepartmentService(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    
    @Transactional(readOnly = true)
    public Department getDepartmentLazy(Long id) {
        Department d = departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));

      
        d.getEmployees().size();
        return d;
    }

    
    @Transactional(readOnly = true)
    public List<Department> getAllWithEmployeesJoinFetch() {
        return departmentRepo.findAllWithEmployeesJoinFetch();
    }

    
    @Transactional(readOnly = true)
    public List<Department> getAllWithEmployeesEntityGraph() {
        return departmentRepo.findAll();
    }
}