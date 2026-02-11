package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

	
	private final UserService userService;
	
	public DashboardController( UserService userService) {
		this.userService=userService;
	}

	@GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        String email = (String) session.getAttribute("userEmail");
        if (email == null) {
            return "redirect:/login";
        }

        model.addAttribute("name", userService.getName(email));
        model.addAttribute("email", email);

        return "dashboard";
    }
}