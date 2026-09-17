package com.sece.student.controller;

import com.sece.student.entity.Studententity;
import com.sece.student.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
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

        // Check whether the username already exists in the database
        Optional<Studententity> existingStudent = studentRepository.findByUsername(student.getUsername());

        // If username already exists → return proper error message
        if (existingStudent.isPresent()) {
            response.put("message", "Username already exists");
            return ResponseEntity.badRequest().body(response);
        }

        // If username is new → save the student and return success message
        Studententity savedStudent = studentRepository.save(student);
        response.put("message", "Registration Successful");
        response.put("studentName", savedStudent.getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Studententity loginRequest) {
        Map<String, String> response = new HashMap<>();

        if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()) {
            response.put("message", "Username is required");
            return ResponseEntity.badRequest().body(response);
        }

        // Check if the username exists
        Optional<Studententity> existingStudent = studentRepository.findByUsername(loginRequest.getUsername());

        // If username does not exist → return "Username not found"
        if (existingStudent.isEmpty()) {
            response.put("message", "Username not found");
            return ResponseEntity.badRequest().body(response);
        }

        Studententity student = existingStudent.get();

        // If password is incorrect → return "Invalid Password"
        if (student.getPassword() == null || !student.getPassword().equals(loginRequest.getPassword())) {
            response.put("message", "Invalid Password");
            return ResponseEntity.badRequest().body(response);
        }

        // If both username and password are correct → return "Login Successful" along with student name
        response.put("message", "Login Successful");
        response.put("studentName", student.getName());
        return ResponseEntity.ok(response);
    }
}
