package com.example.demo.dto;

import java.util.List;

public class DepartmentCreateRequest {

    private String name;
    private List<EmployeeCreateRequest> employees;

    public DepartmentCreateRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<EmployeeCreateRequest> getEmployees() { return employees; }
    public void setEmployees(List<EmployeeCreateRequest> employees) 
    { this.employees = employees; }

    // Inner DTO class (to avoid creating separate file)
    public static class EmployeeCreateRequest {
        private String name;
        private String email;

        public EmployeeCreateRequest() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}