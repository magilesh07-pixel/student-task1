# Postman Testing Guide

## Base URL
`http://localhost:8080`

---

## 1. Student Authentication (`/auth`)

### 1.1 Register Student
- **Method**: `POST`
- **URL**: `http://localhost:8080/auth/register`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "name": "Mahilesh",
  "department": "Computer Science",
  "age": 21,
  "username": "magi01",
  "password": "mypassword123"
}
```

### 1.2 Login Student
- **Method**: `POST`
- **URL**: `http://localhost:8080/auth/login`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "username": "magi01",
  "password": "mypassword123"
}
```

---

## 2. Student Management (`/student`)

### 2.1 Get All Students
- **Method**: `GET`
- **URL**: `http://localhost:8080/student`

### 2.2 Get Student by ID
- **Method**: `GET`
- **URL**: `http://localhost:8080/student/1`

### 2.3 Add Student
- **Method**: `POST`
- **URL**: `http://localhost:8080/student`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "name": "Rahul Sharma",
  "department": "IT",
  "age": 20,
  "username": "rahul",
  "password": "pass123"
}
```

### 2.4 Update Student
- **Method**: `PUT`
- **URL**: `http://localhost:8080/student/1`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "name": "Aravind Kumar Updated",
  "department": "Computer Science",
  "age": 22
}
```

### 2.5 Delete One Student
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/student/1`

### 2.6 Delete Multiple Students
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/student`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
[2, 3]
```

---

## 3. Course Management (`/courses` or `/course`)

### 3.1 Get All Courses
- **Method**: `GET`
- **URL**: `http://localhost:8080/courses`

### 3.2 Get Course by ID
- **Method**: `GET`
- **URL**: `http://localhost:8080/courses/1`

### 3.3 Create Course
- **Method**: `POST`
- **URL**: `http://localhost:8080/courses`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "courseName": "Cloud Computing & DevOps",
  "department": "Computer Science",
  "duration": 6,
  "fees": 30000.0
}
```

### 3.4 Update Course
- **Method**: `PUT`
- **URL**: `http://localhost:8080/courses/1`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "courseName": "Advanced Full Stack Java",
  "department": "Computer Science",
  "duration": 8,
  "fees": 32000.0
}
```

### 3.5 Delete Course
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/courses/1`
