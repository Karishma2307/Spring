package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.*;
import com.example.demo.model.*;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;




@Controller

public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService=userService;
		
	}
	
	@GetMapping("/")
	public String Home() {
		return "redirect:/login";
		
	}
	@GetMapping("/register")
	public String showRegister(Model model)
	{
		model.addAttribute("registerForm",new RegisterForm());
		return "register";
	}
	
	@PostMapping("/register")
	public String doRegister(@Valid @ModelAttribute("registerForm") RegisterForm form,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "register";
		}
		if(userService.exists(form.getEmail())) {
			model.addAttribute("error","Email Already Registered.class Please login");
					return "register";
		}
		userService.register(form.getName(),form.getEmail(),form.getPassword());
		return "redirect:/login?registered=true";
	}

	
	@GetMapping("/login")
	public String showLogin(@RequestParam(required=false) String registered,Model model) {
		model.addAttribute("loginForm",new LoginForm());
		
		if(("true").equals(registered)){
			model.addAttribute("msg","Registration Successful. Please Login");
			
		}
		return "login";
		}
	@PostMapping("/login")
	public String doLoginr(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult result,
			Model model,HttpSession session) {
		
		if(result.hasErrors()) {
			return "login";
		}
		
		boolean ok =userService.validateLogin(form.getEmail(),form.getPassword());
			
				if(!ok)
				{
					model.addAttribute("error","Invalid Email or Password");
					return "login";
				}
				
				System.out.println(" Login success");
				session.setAttribute("userEmail",form.getEmail());
				return "redirect:/dashboard";
	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/login?logout=true";
		
	}
}
