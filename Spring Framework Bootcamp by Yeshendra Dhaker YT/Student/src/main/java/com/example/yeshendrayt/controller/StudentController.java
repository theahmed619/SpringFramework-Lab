package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.entity.Student;
import com.example.yeshendrayt.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("createstudent")
	public ResponseEntity<?> createStudent(@RequestBody Student student){
		return studentService.saveStudent(student);
	}

}
