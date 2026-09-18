package com.sece.expert.controller;

import com.sece.expert.entity.studententity;
import com.sece.expert.repository.studentrepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final studentrepository repository;

    public AuthController(studentrepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody studententity student) {
        Map<String, String> response = new HashMap<>();

        if (student.getUsername() == null || student.getUsername().trim().isEmpty()) {
            response.put("message", "Username is required");
            return ResponseEntity.badRequest().body(response);
        }

        if (student.getPassword() == null || student.getPassword().trim().isEmpty()) {
            response.put("message", "Password is required");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<studententity> existingStudent = repository.findByUsername(student.getUsername());

        if (existingStudent.isPresent()) {
            response.put("message", "Username already exists");
            return ResponseEntity.badRequest().body(response);
        }

        studententity savedStudent = repository.save(student);
        response.put("message", "Registration Successful");
        response.put("studentName", savedStudent.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody studententity loginRequest) {
        Map<String, String> response = new HashMap<>();

        if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
            response.put("message", "Username is required");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<studententity> existingStudent = repository.findByUsername(loginRequest.getUsername());

        if (existingStudent.isEmpty()) {
            response.put("message", "Username not found");
            return ResponseEntity.badRequest().body(response);
        }

        studententity student = existingStudent.get();

        if (student.getPassword() == null || !student.getPassword().equals(loginRequest.getPassword())) {
            response.put("message", "Invalid Password");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", "Login Successful");
        response.put("studentName", student.getName());
        return ResponseEntity.ok(response);
    }
}
