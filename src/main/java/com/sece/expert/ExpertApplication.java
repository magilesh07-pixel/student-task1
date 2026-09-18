package com.sece.expert;

import com.sece.expert.entity.Course;
import com.sece.expert.entity.Enrollment;
import com.sece.expert.entity.Marks;
import com.sece.expert.entity.studententity;
import com.sece.expert.repository.CourseRepository;
import com.sece.expert.repository.EnrollmentRepository;
import com.sece.expert.repository.MarksRepository;
import com.sece.expert.repository.studentrepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpertApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpertApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(studentrepository studentRepo,
                                  CourseRepository courseRepo,
                                  EnrollmentRepository enrollmentRepo,
                                  MarksRepository marksRepo) {
        return args -> {
            if (studentRepo.count() == 0) {
                studentRepo.save(new studententity(0, "Aravind Kumar", "Computer Science", 21, "aravind", "pass123"));
                studentRepo.save(new studententity(0, "Divya Sharma", "Information Technology", 20, "divya", "pass123"));
                studentRepo.save(new studententity(0, "Praveen Raj", "Electronics & Comm", 22, "praveen", "pass123"));
            }

            if (courseRepo.count() == 0) {
                courseRepo.save(new Course(0, "Full Stack Java Development", "Computer Science", 6, 25000.0));
                courseRepo.save(new Course(0, "Data Structures & Algorithms", "Information Technology", 4, 18000.0));
                courseRepo.save(new Course(0, "VLSI & Embedded Systems", "Electronics & Comm", 5, 22000.0));
            }

            if (enrollmentRepo.count() == 0) {
                enrollmentRepo.save(new Enrollment(0, 1, 1, "2026-01-15", "Active"));
                enrollmentRepo.save(new Enrollment(0, 2, 2, "2026-02-01", "Active"));
            }

            if (marksRepo.count() == 0) {
                marksRepo.save(new Marks(0, 1, "Java Programming", 92.5, "A+"));
                marksRepo.save(new Marks(0, 2, "Data Structures", 88.0, "A"));
            }
        };
    }
}
