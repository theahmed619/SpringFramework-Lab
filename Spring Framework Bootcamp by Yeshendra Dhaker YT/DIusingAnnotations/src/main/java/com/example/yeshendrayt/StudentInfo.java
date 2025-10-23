package com.example.yeshendrayt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("studentinfo")
public class StudentInfo {
	
	//@Autowired
	//@Qualifier("science")
	Student student;
	
	public void showDetails() {
		System.out.println(student.stream());
	}

}
