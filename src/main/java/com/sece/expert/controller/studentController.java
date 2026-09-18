package com.sece.expert.controller;

import com.sece.expert.entity.studententity;
import com.sece.expert.repository.studentrepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class studentController {

    private final studentrepository repository;

    public studentController(studentrepository repository) {
        this.repository = repository;
    }

    // GET - All students
    @GetMapping({"/student", "/students"})
    public List<studententity> getAllStudents() {
        return repository.findAll();
    }

    // GET - Single student by id
    @GetMapping({"/student/{id}", "/students/{id}"})
    public studententity getStudentById(@PathVariable int id) {
        return repository.findById(id).orElse(null);
    }

    // POST - Add one student
    @PostMapping({"/student", "/students"})
    public studententity addStudent(@RequestBody studententity student) {
        return repository.save(student);
    }

    // PUT - Update student
    @PutMapping({"/student/{id}", "/students/{id}"})
    public List<studententity> updateStudents(@PathVariable int id, @RequestBody studententity student) {
        studententity oldStudent = repository.findById(id).orElse(null);

        if (oldStudent != null) {
            oldStudent.setName(student.getName());
            oldStudent.setDepartment(student.getDepartment());
            oldStudent.setAge(student.getAge());
            if (student.getUsername() != null) {
                oldStudent.setUsername(student.getUsername());
            }
            if (student.getPassword() != null) {
                oldStudent.setPassword(student.getPassword());
            }

            repository.save(oldStudent);
        }

        return repository.findAll();
    }

    // DELETE - Delete one student
    @DeleteMapping({"/student/{id}", "/students/{id}"})
    public String deleteStudent(@PathVariable int id) {
        studententity student = repository.findById(id).orElse(null);
        if (student != null) {
            repository.deleteById(id);
            return "Student deleted successfully!";
        }
        return "Student not found!";
    }

    // DELETE - Delete multiple students
    @DeleteMapping({"/student", "/students"})
    public String deleteStudents(@RequestBody List<Integer> ids) {
        repository.deleteAllById(ids);
        return "Students deleted successfully!";
    }
}
