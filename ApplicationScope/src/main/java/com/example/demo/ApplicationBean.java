package com.example.demo;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope   
public class ApplicationBean {

    private final String appId;

    public ApplicationBean() {
        this.appId = UUID.randomUUID().toString();
        System.out.println("AppBean Created (Only once for whole app): " + appId);
    }

    public String getAppId() {
        return appId;
    }
}
