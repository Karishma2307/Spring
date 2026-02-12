
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final EmployeeLifeCycleService service;

    public DemoController(EmployeeLifeCycleService service) {
        this.service = service;
    }

    @GetMapping("/lifecycle")
    public String runLifeCycle() {
        service.demoLifecycle();
        return "Lifecycle demo executed. Check console logs + H2 DB.";
    }
}
