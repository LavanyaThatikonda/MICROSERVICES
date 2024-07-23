package com.studentservice.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.studentservice.entity.Library;
import com.studentservice.entity.Student;
import com.studentservice.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentControl {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/create")
	public Student createStudent( @RequestBody Student student) {
		return studentService.createStudent(student);
		
	}
	@GetMapping("/get/{id}")
     public Student getStudentById(@PathVariable int id) {
    	 Library library=restTemplate.getForObject("http://localhost:8082/library/get/" + id, Library.class);
    	 Student  student=studentService.getStudentById(id);
    	 student.setLibrary(library);
    	 return student;
     }
	
	@PutMapping("/update/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
		
	}
	
    @DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable int id) {
		studentService.deleteStudent(id);
		return "id deleted sucessfully";
	}
    @GetMapping("/getall")
    public List<Student> getall(){
    return	studentService.gellallStudents();
    	
    }
}
