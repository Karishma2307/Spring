package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final LifeCycleService lifeCycleService;

    
    public TestController(LifeCycleService lifeCycleService) {
        this.lifeCycleService = lifeCycleService;
        System.out.println("Constructor: TestController created & bean injected");
    }

    @GetMapping("/life")
    public String checkLifeCycle() {
        System.out.println("Controller Method: Bean is being used now");
        return lifeCycleService.getMessage();
    }
}
