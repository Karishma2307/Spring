package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.*;
public interface EmployeeRepository  extends JpaRepository<Employee,Long>{

	

}