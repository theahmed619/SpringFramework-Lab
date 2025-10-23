package com.example.yeshendrayt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.Student;
import com.example.yeshendrayt.repository.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepo studentRepo;
	
	public ResponseEntity<?> saveStudent(Student student){
		
		studentRepo.save(student);
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}

}
