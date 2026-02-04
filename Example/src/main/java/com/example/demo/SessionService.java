package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class SessionService {
public SessionService() {
	System.out.println("Session Service");
}
public String getMessage() {
	return"Session Service called";
}
}
