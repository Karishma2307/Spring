package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.repo.BankRepository;
import com.example.demo.service.BankService;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SpringJdbcTxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcTxDemoApplication.class, args);
	}

	@Bean
    CommandLineRunner run(BankService bankService, BankRepository bankRepository) {
        return args ->{
			System.out.println("\n ==== BEFORE ====");
			printAll(bankRepository);
			
			
			try {
				bankService.transfer(1,2,200,false);
			}
			catch(Exception e) {
				System.out.println("Success call Exception : "+e.getMessage());
			}
			 System.out.println("\n=== AFTER SUCCESS ===");
	            printAll(bankRepository);
	            
	      
	            try {
	                bankService.transfer(1, 2, 300, true);
	            } catch (Exception e) {
	                System.out.println("FAIL call exception: " + e.getMessage());
	            }

	            System.out.println("\n=== AFTER FAILURE (rollback expected) ===");
	            printAll(bankRepository);
	            System.out.println("\nOpen H2 Console: http://localhost:8080/h2-console");
	            System.out.println("JDBC URL: jdbc:h2:mem:txdb");
        };
	}

	private void printAll(BankRepository repo) {
		System.out.println("Accounts : ");
		repo.getAllAccounts().forEach(System.out::println);

		System.out.println("Audit Logs : ");
		repo.getAllAudits().forEach(System.out::println);
	}
}