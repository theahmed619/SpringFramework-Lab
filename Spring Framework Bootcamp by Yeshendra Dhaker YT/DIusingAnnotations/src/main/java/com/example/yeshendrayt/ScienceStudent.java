package com.example.yeshendrayt;

import org.springframework.stereotype.Component;

@Component("science")
public class ScienceStudent implements Student{

	@Override
	public String stream() {
		return "im science student";
		
	}
	
	

}
