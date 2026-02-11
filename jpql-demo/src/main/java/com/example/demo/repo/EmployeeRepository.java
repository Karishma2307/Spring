
package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	 // ✅ 1) Derived Query (Spring Data creates JPQL internally)
    List<Employee> findBySalaryGreaterThanEqualOrderBySalaryDesc(double minSalary);

    // ✅ 2) JPQL using @Query (Entity + fields)
    @Query("SELECT e FROM Employee e WHERE e.salary >= :minSalary ORDER BY e.salary DESC")
    List<Employee> findBySalaryJPQL(@Param("minSalary") double minSalary);

    // ✅ 3) Native SQL using @Query (table + columns)
    @Query(value = "SELECT * FROM employees WHERE salary >= :minSalary ORDER BY salary DESC", nativeQuery = true)
    List<Employee> findBySalaryNative(@Param("minSalary") double minSalary);
}
