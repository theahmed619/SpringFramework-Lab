package com.example.yeshendrayt;

public class Microsoft implements Brand {
	
	Processors processors;
	
	public Microsoft(Processors processors) {
		this.processors=processors;
	}
	

	@Override
	public void showDetails() {
		System.out.println("You have selected Microsoft Laptop" +processors.showProcessorDetails());
		
	}
}
