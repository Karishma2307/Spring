package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentCreateRequest;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repo.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository deptRepo;

    public DepartmentService(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }

//    // Create Department + Employees (real-time sample)
//    public Department createSampleDepartment() {
//        Department dept = new Department("IT Department");
//
//        Employee e1 = new Employee("Arjun Kumar", "arjun@company.com");
//        Employee e2 = new Employee("Priya Sharma", "priya@company.com");
//        Employee e3 = new Employee("Ravi Singh", "ravi@company.com");
//
//        dept.addEmployee(e1);
//        dept.addEmployee(e2);
//        dept.addEmployee(e3);
//
//        return deptRepo.save(dept); // cascade saves employees also
//    }
    
    // ✅ NEW: create department from Postman JSON
    public Department createDepartment(DepartmentCreateRequest req) {

        Department dept = new Department(req.getName());

        if (req.getEmployees() != null) {
            for (DepartmentCreateRequest.EmployeeCreateRequest e : req.getEmployees()) {
                Employee emp = new Employee(e.getName(), e.getEmail());
                dept.addEmployee(emp); // IMPORTANT: sets department FK also
            }
        }

        return deptRepo.save(dept);
    }

    public List<Department> getAllDepartments() {
        return deptRepo.findAll();
    }

    public Department getDepartmentById(Long id) {
        return deptRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found: " + id));
    }

    public void deleteDepartment(Long id) {
        deptRepo.deleteById(id);
    }
}
