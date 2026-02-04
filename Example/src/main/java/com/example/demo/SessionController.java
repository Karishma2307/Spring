package com.example.demo;


import org.springframework.web.bind.annotation.*;



@RestController
public class SessionController {
	private final SessionService sessionService;
	public SessionController(SessionService sessionService) {
		this.sessionService=sessionService;
	}
	@GetMapping("/session")
	public String getsession() {
		return sessionService.getMessage();
	}

}
