package com.example.demo.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;



@Service
public class UserService {

	private final Map<String, String> users=new HashMap<>();
	private final Map<String, String> names=new HashMap<>();
	
	public void register(String name, String email, String password) {
		users.put(email, password);
		names.put(email, name);
	}
	
	
	public boolean exists(String email) {
		return users.containsKey(email);
	}

	public boolean validateLogin(String email,String password) {
		return users.containsKey(email) && users.get(email).equals(password);
		
	}
	
	public String getName(String email)
	{
		return names.getOrDefault(email, "User");
	}
	
	
}