package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class MobilePhone {

    @Autowired
    private SimCard simCard;   //  Field Injection

    public void makeCall() {
        System.out.println(simCard.connect());
        System.out.println("📞 Making a call...");
    }
}


