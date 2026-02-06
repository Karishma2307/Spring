package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Value("#{101}")
	private int employeeId;
	
    @Value("${emp.name}")
    private String name;

    @Value("#{${emp.salary} + ${emp.bonus}}")
    private int totalSalary;

    @Value("#{${emp.salary} > 50000 ? 'Senior Employee' : 'Junior Employee'}")
    private String grade;
    
    @Value("#{salaryUtil.calculateTax(${emp.salary})}")
    private int tax;

    @Value("#{'${emp.name}'.toUpperCase()}")
    private String upperName;
    
    @Value("#{'${emp.skills}'.split(',')}")
    private List<String> skills;

    
    @Value("#{ {'role':'Developer', 'location':'Bangalore'} }")
    private Map<String, String> details;

    public String getEmployeeDetails() {
        return """
                Employee ID: %d
                Name: %s
                Upper Name: %s
                Total Salary: %d
                Tax: %d
                Grade: %s
                Skills: %s
                Details: %s
                """.formatted(employeeId, name, upperName,
                        totalSalary, tax, grade, skills, details);
    }


}