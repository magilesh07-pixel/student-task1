package com.sece.student.controller;

import com.sece.student.entity.Studententity;
import com.sece.student.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final StudentRepository studentRepository;

    public AuthController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Studententity student) {
        Map<String, String> response = new HashMap<>();

        if (student.getUsername() == null || student.getUsername().trim().isEmpty()) {
            response.put("message", "Username is required");
            return ResponseEntity.badRequest().body(response);
        }

        if (student.getPassword() == null || student.getPassword().trim().isEmpty()) {
            response.put("message", "Password is required");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Studententity> existingStudent = studentRepository.findByUsername(student.getUsername());

        if (existingStudent.isPresent()) {
            response.put("message", "Username already exists");
            return ResponseEntity.badRequest().body(response);
        }

        Studententity savedStudent = studentRepository.save(student);
        response.put("message", "Registration Successful");
        response.put("studentName", savedStudent.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Studententity loginRequest) {
        Map<String, String> response = new HashMap<>();

        Optional<Studententity> existingStudent = studentRepository.findByUsername(loginRequest.getUsername());

        if (existingStudent.isEmpty()) {
            response.put("message", "Username not found");
            return ResponseEntity.badRequest().body(response);
        }

        Studententity student = existingStudent.get();

        if (!student.getPassword().equals(loginRequest.getPassword())) {
            response.put("message", "Invalid Password");
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", "Login Successful");
        response.put("studentName", student.getName());
        return ResponseEntity.ok(response);
    }
}
