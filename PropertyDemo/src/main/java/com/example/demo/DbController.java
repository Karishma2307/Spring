package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DbController {
	private final DatabaseConfig databaseConfig;

	public DbController(DatabaseConfig databaseConfig) {
		this.databaseConfig = databaseConfig;
	}
	
	@GetMapping("/db")
	public String getDbnfo() {
		return databaseConfig.getDbDetails();

}
}