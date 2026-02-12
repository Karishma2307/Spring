package com.example.demo.repo;


import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

   
    @Query("select distinct d from Department d left join fetch d.employees")
    List<Department> findAllWithEmployeesJoinFetch();

    
    @EntityGraph(attributePaths = "employees")
    List<Department> findAll();
}