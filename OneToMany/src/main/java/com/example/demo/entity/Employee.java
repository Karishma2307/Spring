
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto PK
    private Long id;

    @Column(name = "emp_name", nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 120)
    private String email;

    // Many Employees -> One Department
    @ManyToOne
    @JoinColumn(name = "department_id") // FK column in employees table
    @JsonBackReference
    private Department department;

    public Employee() {}

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters/Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
