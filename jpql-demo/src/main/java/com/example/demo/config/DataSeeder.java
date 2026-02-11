package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository repo;

    public DataSeeder(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Employee("Arjun Kumar", "arjun@company.com", 45000));
        repo.save(new Employee("Priya Sharma", "priya@company.com", 60000));
        repo.save(new Employee("Rahul Verma", "rahul@company.com", 75000));
        repo.save(new Employee("Neha Singh", "neha@company.com", 50000));
        repo.save(new Employee("Vikram Iyer", "vikram@company.com", 90000));

        System.out.println("✅ Seeded employees into H2");
    }
}