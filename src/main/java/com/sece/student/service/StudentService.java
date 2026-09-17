package com.sece.student.service;

import com.sece.student.entity.Studententity;
import com.sece.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Studententity> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Studententity> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Studententity saveStudent(Studententity student) {
        return studentRepository.save(student);
    }

    public Optional<Studententity> updateStudent(int id, Studententity studentDetails) {
        return studentRepository.findById(id).map(existingStudent -> {
            if (studentDetails.getName() != null) {
                existingStudent.setName(studentDetails.getName());
            }
            if (studentDetails.getDepartment() != null) {
                existingStudent.setDepartment(studentDetails.getDepartment());
            }
            if (studentDetails.getAge() > 0) {
                existingStudent.setAge(studentDetails.getAge());
            }
            if (studentDetails.getUsername() != null && !studentDetails.getUsername().trim().isEmpty()) {
                existingStudent.setUsername(studentDetails.getUsername());
            }
            if (studentDetails.getPassword() != null && !studentDetails.getPassword().trim().isEmpty()) {
                existingStudent.setPassword(studentDetails.getPassword());
            }
            return studentRepository.save(existingStudent);
        });
    }

    public boolean deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Studententity> findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public long count() {
        return studentRepository.count();
    }
}
