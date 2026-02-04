package com.example.demo;

import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope   
public class SessionBean {

    private final String sessionId;

    public SessionBean() {
        this.sessionId = UUID.randomUUID().toString();
        System.out.println("SessionBean Created: " + sessionId);
    }

    public String getSessionId() {
        return sessionId;
    }
}
