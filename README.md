# SCMS - Student Course Management System (Spring Boot Project)

SCMS (Student Course Management System) is a full-stack Spring Boot application that manages student information, course catalog, and enrollment data. This project demonstrates CRUD operations, relationships between entities (One-to-Many, Many-to-One), and REST API handling using Spring Data JPA and Spring Web.

## 🔧 Features

- Add, update, delete and view Students
- Add, update, delete and view Courses
- Enroll students in courses
- Display enrollments with student and course details
- JSON REST API interaction (Postman-compatible)
- Clean DTO-based API responses to avoid circular references

## 🧰 Tools & Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database (in-memory)**
- **Postman** for testing APIs
- **Maven** for dependency management
- **Spring Tool Suite (STS)** as IDE

## 🗂️ Project Structure

- `Student` entity: stores basic student data
- `Course` entity: stores course details
- `Enrollment` entity: joins students and courses (Many-to-One with both)
- DTOs used to avoid nested circular responses

## 📦 How to Run

1. Clone the repo  
   `git clone https://github.com/Yashsadarangani/SCMS_SpringBoot.git`

2. Import into Spring Tool Suite (STS) or any IDE

3. Run the application as a Spring Boot App

4. Access H2 Console at:  
   `http://localhost:8080/h2-console`  
   - JDBC URL: `jdbc:h2:mem:testdb`

5. Use Postman or browser to interact with endpoints like:  
   - `/students`  
   - `/courses`  
   - `/enrollments`

## 📮 API Sample (POST Enrollment)

```json
{
  "studentId": 1,
  "courseId": 2
}
