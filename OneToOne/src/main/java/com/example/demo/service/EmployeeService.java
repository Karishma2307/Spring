package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.repo.EmployeeRepository;


@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee createEmoployeeWithProfile(String name, String email, String phone, String address) {
		Employee employee = new Employee(name, email);
		EmployeeProfile profile = new EmployeeProfile(phone, address);

		employee.setProfile(profile);
		return employeeRepository.save(employee);
	}

	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	public Employee getById(Long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found : " + id));
	}
	
	public void deleteById(Long id)
	{
		employeeRepository.deleteById(id);
	}
	  
    @Transactional
    public Employee updateEmployeeWithProfile(Long id, String name, String email, String phone, String address) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found : " + id));

      
        employee.setName(name);
        employee.setEmail(email);

        
        EmployeeProfile profile = employee.getProfile();
        if (profile == null) {
            profile = new EmployeeProfile();
            employee.setProfile(profile);
        }

        profile.setPhone(phone);
        profile.setAddress(address);

        
        return employeeRepository.save(employee);
    }
}