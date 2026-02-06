package com.example.demo;

public class Employee {

	private String name;
	private int salary;
	private int bonus;
	private int totalSalary;
	private String grade;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(int totalSalary) {
		this.totalSalary = totalSalary;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getDetails() {
		return "Name: " + name + ", Salary: " + salary + ", Bonus: " + bonus + ", Total Salary: " + totalSalary
				+ ", Grade: " + grade;
	}


}