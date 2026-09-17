package com.sece.student;

import com.sece.student.entity.Course;
import com.sece.student.entity.Studententity;
import com.sece.student.repository.CourseRepository;
import com.sece.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository, CourseRepository courseRepository) {
        return args -> {
            if (studentRepository.count() == 0) {
                studentRepository.save(new Studententity(0, "Aravind Kumar", "Computer Science", 21, "aravind", "pass123"));
                studentRepository.save(new Studententity(0, "Divya Sharma", "Information Technology", 20, "divya", "pass123"));
                studentRepository.save(new Studententity(0, "Praveen Raj", "Electronics & Comm", 22, "praveen", "pass123"));
            }

            if (courseRepository.count() == 0) {
                courseRepository.save(new Course(0, "Full Stack Java Development", "Computer Science", 6, 25000.0));
                courseRepository.save(new Course(0, "Data Structures & Algorithms", "Information Technology", 4, 18000.0));
                courseRepository.save(new Course(0, "VLSI & Embedded Systems", "Electronics & Comm", 5, 22000.0));
            }
        };
    }
}
