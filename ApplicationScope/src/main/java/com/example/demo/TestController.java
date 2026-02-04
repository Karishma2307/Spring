package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ApplicationBean appBean;

    public TestController(ApplicationBean appBean) {
        this.appBean = appBean;
    }

    @GetMapping("/app-test")
    public String appScopeTest() {
        return "Application ID: " + appBean.getAppId()
                + " | Bean hashCode: " + appBean.hashCode();
    }
}
