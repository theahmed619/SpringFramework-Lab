package com.example.yeshendrayt;

public class MacBook implements Brand{
	
	Processors processors;
	
	public MacBook(Processors processors) {
		this.processors=processors;
	}
	

	@Override
	public void showDetails() {
		System.out.println("You have selected MacBook Laptop"+processors.showProcessorDetails());
		
	}

}
