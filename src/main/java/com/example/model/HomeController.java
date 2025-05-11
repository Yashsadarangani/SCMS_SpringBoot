package com.example.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	//FOR STUDENTS TABLE
	@Autowired
	StudentRepo srepo;
	
	@GetMapping("/Students")
	public List<Student> getStudents(){
		return srepo.findAll();
	}
	
	@PostMapping("/Students")
	public Student addStudent(@RequestBody Student s){
		srepo.save(s);
		return s;
	}
	
	@GetMapping("/Students/{ID}")
	public Optional<Student> getStudent(@PathVariable(name = "ID") long id) {
		return srepo.findById(id);
	}
	
	@PutMapping("/Students/{ID}")
	public Student EditOrSaveStudent(@PathVariable(name = "ID") long id,@RequestBody Student s){
		Student sd = srepo.findById(id).orElseThrow();
		sd.setEmail(s.getEmail());
		sd.setName(s.getName());
		sd.setEnrollments(s.getEnrollments());
		srepo.delete(s);
		srepo.save(sd);
		return sd;
	}
	
	@DeleteMapping("/Students/{ID}")
	public String deleteStudent(@PathVariable(name = "ID") long id) {
		srepo.deleteById(id);
		return "Deleted";
	}
	
	//FOR COURSE TABLE
	@Autowired
	CourseRepo crepo;
	
	@GetMapping("/Courses")
	public List<Course> getCourses(){
		return crepo.findAll();
	}
	
	@GetMapping("/Courses/{ID}")
	public Course getCourse(@PathVariable (name = "ID") long id) {
		return crepo.findById(id).orElseThrow();
	}
	
	@PostMapping("/Courses")
	public Course addCourse(@RequestBody Course c) {
		crepo.save(c);
		return c;
	}
	
	@PutMapping("/Courses/{ID}")
	public Course EditOrSaveCourse(@PathVariable (name = "ID") long id, @RequestBody Course c) {
		Course cd = crepo.findById(id).orElseThrow();
		cd.setTitle(c.getTitle());
		cd.setDescription(c.getDescription());
		cd.setEnrollments(c.getEnrollments());
		crepo.delete(c);
		crepo.save(cd);
		return cd;
	}
	
	@DeleteMapping("/Courses/{ID}")
	public String deleteCourse(@PathVariable ( name = "ID" ) long id) {
		Course c=crepo.findById(id).orElseThrow();
		crepo.delete(c);
		return "Deleted";
	}
	
	
	//FOR ENROLLMENT TABLE
	
	@Autowired
	EnrollmentRepo erepo;
	
	@GetMapping("/Enrollments")
	public List<Enrollment> getEnrollments(){
		return erepo.findAll();
	}
	
	@PostMapping("/Enrollments")
	public ResponseEntity<String> createEnrollment(@RequestBody EnrollmentDTO dto) {
        Student student = srepo.findById(dto.getStudent_id())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = crepo.findById(dto.getCourse_id())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        erepo.save(enrollment);

        return ResponseEntity.ok("Enrollment created successfully");
    }
	
	@DeleteMapping("/Enrollment/{ID}")
	public String deleteEnrollment(@PathVariable (name="ID") long id) {
		Enrollment ed = erepo.findById(id).orElseThrow();
		erepo.delete(ed);
		return "Deleted";
	}
}
