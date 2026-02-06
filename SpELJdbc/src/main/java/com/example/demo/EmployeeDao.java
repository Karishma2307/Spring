package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

	
	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	public EmployeeDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	public List<Employee> getallEmployees() {
		String sql = "Select * from Employee";
		return namedJdbcTemplate.query(sql, new EmployeeRowMapper());
	}
	
	public Employee getEmployeeById(int id)
	{
		String sql="Select * from Employee where id=:id";
		return namedJdbcTemplate.queryForObject(sql,Map.of("id",id),
				new EmployeeRowMapper());
	}
	
	public int addEmployee(Employee emp)
	{
		String sql="""				
				insert into Employee (id,name,salary)
				 values(:id,:name,:salary)
				""";		
		return namedJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(emp));
				
	}
	
}