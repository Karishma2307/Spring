package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;



@Service
public class MessageService {
	public  MessageService() {
		System.out.println("MessageService Constructor Called : Bean Created");
	}
	@GetMapping("/singleton")
	public String getMessage() {
		return  "Hello From MessagService class :Singleton Bean";
	}
}