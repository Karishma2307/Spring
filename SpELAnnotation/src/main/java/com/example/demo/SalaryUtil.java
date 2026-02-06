package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class SalaryUtil {
    public int calculateTax(int salary) {
        return salary > 50000 ? 5000 : 2000;
    }

}
