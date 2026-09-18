package com.sece.expert.controller;

import com.sece.expert.entity.Enrollment;
import com.sece.expert.repository.EnrollmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class EnrollmentController {

    private final EnrollmentRepository repository;

    public EnrollmentController(EnrollmentRepository repository) {
        this.repository = repository;
    }

    // GET all enrollments
    @GetMapping({"/enrollment", "/enrollments"})
    public List<Enrollment> getAllEnrollments() {
        return repository.findAll();
    }

    // GET enrollment by ID
    @GetMapping({"/enrollment/{id}", "/enrollments/{id}"})
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create enrollment
    @PostMapping({"/enrollment", "/enrollments"})
    public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = repository.save(enrollment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // PUT update enrollment
    @PutMapping({"/enrollment/{id}", "/enrollments/{id}"})
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable int id, @RequestBody Enrollment details) {
        Optional<Enrollment> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Enrollment existing = opt.get();
        if (details.getStudentId() > 0) existing.setStudentId(details.getStudentId());
        if (details.getCourseId() > 0) existing.setCourseId(details.getCourseId());
        if (details.getEnrollmentDate() != null) existing.setEnrollmentDate(details.getEnrollmentDate());
        if (details.getStatus() != null) existing.setStatus(details.getStatus());

        return ResponseEntity.ok(repository.save(existing));
    }

    // DELETE enrollment
    @DeleteMapping({"/enrollment/{id}", "/enrollments/{id}"})
    public String deleteEnrollment(@PathVariable int id) {
        if (!repository.existsById(id)) {
            return "Enrollment not found!";
        }
        repository.deleteById(id);
        return "Enrollment deleted successfully!";
    }
}
