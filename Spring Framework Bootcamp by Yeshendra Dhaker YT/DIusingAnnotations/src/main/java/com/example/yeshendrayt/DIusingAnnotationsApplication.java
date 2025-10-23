package com.example.yeshendrayt;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class DIusingAnnotationsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DIusingAnnotationsApplication.class, args);
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.example.yeshendrayt");
			System.out.println("Choose the Stream: \n1. Science\n2. Commerce");
			Scanner  scanner=new Scanner(System.in);
			
			int userInput=scanner.nextInt();
			String selectedStram="";
			switch(userInput) {
			case 1:{
				
				selectedStram="science"; //componenet id 
				
				break;
			}
			case 2:{
				
				selectedStram="commerce";
				break;
			}
			}
			
			Student student=(Student)context.getBean(selectedStram);
			StudentInfo studentInfo=context.getBean(StudentInfo.class);
			studentInfo.student=student;
			studentInfo.showDetails();
	}

}
