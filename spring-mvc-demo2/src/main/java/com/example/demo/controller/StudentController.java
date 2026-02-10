package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // 1) Show Form (GET)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // 2) Handle Form Submit (POST)
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            return "student-form"; // show same page with errors
        }

        service.addStudent(student);

        model.addAttribute("msg", "Student Registered Successfully!");
        return "success";
    }

    // 3) List students (GET)
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "student-list";
    }

    // 4) Delete student (GET) - for learning
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam String email) {
        service.deleteByEmail(email);
        return "redirect:/students"; // go back to list
    }
}