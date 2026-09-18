# Enrollment and Marks Testing Guide

## 1. Enrollment API (`/enrollment` or `/enrollments`)

### 1.1 Get All Enrollments
- **Method**: `GET`
- **URL**: `http://localhost:8080/enrollment`

### 1.2 Get Enrollment by ID
- **Method**: `GET`
- **URL**: `http://localhost:8080/enrollment/1`

### 1.3 Add Enrollment
- **Method**: `POST`
- **URL**: `http://localhost:8080/enrollment`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "studentId": 1,
  "courseId": 2,
  "enrollmentDate": "2026-03-01",
  "status": "Active"
}
```

### 1.4 Update Enrollment
- **Method**: `PUT`
- **URL**: `http://localhost:8080/enrollment/1`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "studentId": 1,
  "courseId": 1,
  "enrollmentDate": "2026-01-15",
  "status": "Completed"
}
```

### 1.5 Delete Enrollment
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/enrollment/1`

---

## 2. Marks API (`/marks` or `/mark`)

### 2.1 Get All Marks
- **Method**: `GET`
- **URL**: `http://localhost:8080/marks`

### 2.2 Get Mark by ID
- **Method**: `GET`
- **URL**: `http://localhost:8080/marks/1`

### 2.3 Add Mark
- **Method**: `POST`
- **URL**: `http://localhost:8080/marks`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "studentId": 1,
  "subject": "Cloud Computing",
  "marks": 95.0,
  "grade": "O"
}
```

### 2.4 Update Mark
- **Method**: `PUT`
- **URL**: `http://localhost:8080/marks/1`
- **Headers**: `Content-Type: application/json`
- **Body**:
```json
{
  "studentId": 1,
  "subject": "Java Programming",
  "marks": 98.0,
  "grade": "O"
}
```

### 2.5 Delete Mark
- **Method**: `DELETE`
- **URL**: `http://localhost:8080/marks/1`
