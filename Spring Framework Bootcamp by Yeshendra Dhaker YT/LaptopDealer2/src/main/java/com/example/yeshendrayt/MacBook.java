package com.example.yeshendrayt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MacBook implements Brand{
	
	//@Autowired
	Processors processors;
	
	

	@Override
	public void showDetails() {
		System.out.println("You have selected MacBook Laptop"+processors.showProcessorDetails());
		
	}

}
