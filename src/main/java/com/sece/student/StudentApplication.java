package com.sece.student;

import com.sece.student.entity.Studententity;
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
    CommandLineRunner initDatabase(StudentRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Studententity(0, "Aravind Kumar", "Computer Science", 21, "aravind", "pass123"));
                repository.save(new Studententity(0, "Divya Sharma", "Information Technology", 20, "divya", "pass123"));
                repository.save(new Studententity(0, "Praveen Raj", "Electronics & Comm", 22, "praveen", "pass123"));
            }
        };
    }
}
