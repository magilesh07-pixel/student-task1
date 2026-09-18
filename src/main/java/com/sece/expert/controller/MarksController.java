package com.sece.expert.controller;

import com.sece.expert.entity.Marks;
import com.sece.expert.repository.MarksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class MarksController {

    private final MarksRepository repository;

    public MarksController(MarksRepository repository) {
        this.repository = repository;
    }

    // GET all marks
    @GetMapping({"/marks", "/mark"})
    public List<Marks> getAllMarks() {
        return repository.findAll();
    }

    // GET mark by ID
    @GetMapping({"/marks/{id}", "/mark/{id}"})
    public ResponseEntity<Marks> getMarkById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create mark
    @PostMapping({"/marks", "/mark"})
    public ResponseEntity<Marks> addMarks(@RequestBody Marks marks) {
        Marks saved = repository.save(marks);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // PUT update mark
    @PutMapping({"/marks/{id}", "/mark/{id}"})
    public ResponseEntity<Marks> updateMarks(@PathVariable int id, @RequestBody Marks details) {
        Optional<Marks> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Marks existing = opt.get();
        if (details.getStudentId() > 0) existing.setStudentId(details.getStudentId());
        if (details.getSubject() != null) existing.setSubject(details.getSubject());
        if (details.getMarks() >= 0) existing.setMarks(details.getMarks());
        if (details.getGrade() != null) existing.setGrade(details.getGrade());

        return ResponseEntity.ok(repository.save(existing));
    }

    // DELETE mark
    @DeleteMapping({"/marks/{id}", "/mark/{id}"})
    public String deleteMarks(@PathVariable int id) {
        if (!repository.existsById(id)) {
            return "Marks not found!";
        }
        repository.deleteById(id);
        return "Marks deleted successfully!";
    }
}
