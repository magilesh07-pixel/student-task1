package com.sece.expert.controller;

import com.sece.expert.entity.Course;
import com.sece.expert.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    // GET all courses
    @GetMapping({"/course", "/courses"})
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    // GET course by ID
    @GetMapping({"/course/{id}", "/courses/{id}"})
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create course
    @PostMapping({"/course", "/courses"})
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = repository.save(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // PUT update course
    @PutMapping({"/course/{id}", "/courses/{id}"})
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course courseDetails) {
        Optional<Course> existingCourseOpt = repository.findById(id);

        if (existingCourseOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course existingCourse = existingCourseOpt.get();
        if (courseDetails.getCourseName() != null) {
            existingCourse.setCourseName(courseDetails.getCourseName());
        }
        if (courseDetails.getDepartment() != null) {
            existingCourse.setDepartment(courseDetails.getDepartment());
        }
        if (courseDetails.getDuration() > 0) {
            existingCourse.setDuration(courseDetails.getDuration());
        }
        if (courseDetails.getFees() >= 0) {
            existingCourse.setFees(courseDetails.getFees());
        }

        Course updatedCourse = repository.save(existingCourse);
        return ResponseEntity.ok(updatedCourse);
    }

    // DELETE course
    @DeleteMapping({"/course/{id}", "/courses/{id}"})
    public String deleteCourse(@PathVariable int id) {
        if (!repository.existsById(id)) {
            return "Course not found!";
        }

        repository.deleteById(id);
        return "Course deleted successfully!";
    }
}
