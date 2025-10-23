package com.example.yeshendrayt;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		//SpringApplication.run(Demo1Application.class, args);
		//Optional<String> name=getName();
		
		//if(name!=null)
		//System.out.println(name.toLowerCase());
		
//		if(name.isPresent()) {
//			System.out.println(name.get().toLowerCase());
//		}
//		
//		name.ifPresent(x->System.out.println(x.toLowerCase()));
//		String res=name.orElseThrow(()->new RuntimeException("NOT FOUND"));
//		System.out.println(res);
		
		
		//enums
		
//		DaysOfWeek daysOfWeek= DaysOfWeek.MONDAY;
//		System.out.println(daysOfWeek);
		for(DaysOfWeek day:DaysOfWeek.values()) {
			System.out.println(day);
		}
		
	}

	//optional 
	public static Optional<String> getName() {
		
		return Optional.ofNullable(null);
	}
}
