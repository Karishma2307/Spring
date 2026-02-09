package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="emp_name", nullable=false,length=100)
	private String name;
	
	
	
	@Column(unique=true, nullable=false,length=130)
	private String email;
	
	
	@OneToOne(mappedBy="employee",cascade=CascadeType.ALL, orphanRemoval=true)
	private EmployeeProfile profile;
	
	public Employee() {}
	
	public Employee(String name,String email) {
		this.name=name;
		this.email=email;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeProfile getProfile() {
		return profile;
	}

	public void setProfile(EmployeeProfile profile)
	{
		this.profile=profile;
		if(profile!=null) {
			profile.setEmployee(this);
		}
	}
	

}