package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
	private final RequestBean requestBean;
	
	public TestController(RequestBean requestBean) {
		this.requestBean=requestBean;
	}
	@GetMapping("/test")
	public String testRequestScope() {
		return "Request ID: " + requestBean.getRequestId()
        + " | Bean hashCode: " + requestBean.hashCode();

		}
	

}
