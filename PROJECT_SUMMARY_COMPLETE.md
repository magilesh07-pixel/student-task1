# Project Complete Summary

## Project Structure Overview
```
src
├── main
│   ├── java/com/sece/expert
│   │   ├── controller
│   │   │   ├── AuthController.java
│   │   │   ├── CourseController.java
│   │   │   ├── EnrollmentController.java
│   │   │   ├── MarksController.java
│   │   │   └── studentController.java
│   │   ├── entity
│   │   │   ├── Course.java
│   │   │   ├── Enrollment.java
│   │   │   ├── Marks.java
│   │   │   └── studententity.java
│   │   ├── repository
│   │   │   ├── CourseRepository.java
│   │   │   ├── EnrollmentRepository.java
│   │   │   ├── MarksRepository.java
│   │   │   └── studentrepository.java
│   │   └── ExpertApplication.java
│   └── resources
│       └── application.properties
└── test/java/com/sece/expert
    └── ExpertApplicationTests.java
```

## Running the Application
```powershell
.\mvnw.cmd spring-boot:run
```
Base URL: `http://localhost:8080`
H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:studentdb`, Username: `sa`)
