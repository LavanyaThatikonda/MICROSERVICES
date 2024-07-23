package com.studentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.studentservice.entity.Student;
import com.studentservice.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public Student createStudent(Student student) {
		if(studentRepository.findByName(student.getName())!=null) {
			throw new RuntimeException("student name is alredy exist");
		}
		return studentRepository.save(student);
	}

	 public Student getStudentById(int id) {
	        // Use Optional to handle the absence of the library
	        Optional<Student> optionalStudent = studentRepository.findById(id);

	        // Check if the library is present, otherwise throw an exception
	        if (!optionalStudent.isPresent()) {
	            throw new RuntimeException("Library with this ID does not exist");
	        }

	        // Return the library if present
	        return optionalStudent.get();
	    }
	
	public Student updateStudent(int id, Student student) {
		Student existingStudent=studentRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("id not found"));
		existingStudent.setName(student.getName());
		existingStudent.setRollNumber(student.getRollNumber());
		existingStudent.setEmail(student.getEmail());
		return studentRepository.save(student);
	}
	
	public void deleteStudent(int id) {
		Student student=studentRepository.findById(id)
				.orElseThrow(()->new RuntimeException("id not found"));
		studentRepository.deleteById(id);
		
	}
	
	public List<Student> gellallStudents(){
		return studentRepository.findAll();
	}
}
