package com.example.demo;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	
	@Value("${employee.name}")
	private String name;
	
	@Value("#{${employee.salary}+${employee.bonus}}")
	private String totalSalary;
	
	
	@Value("#{${employee.salary}>50000?'Senior Employee':'Junior Employee'}")
	private String employeeType;
	
	public String getemployeeDetails() {
		return "Name : "+name +"<br/> Total Salary : "+totalSalary +"<br/> Type : "+employeeType;
	}
	
}