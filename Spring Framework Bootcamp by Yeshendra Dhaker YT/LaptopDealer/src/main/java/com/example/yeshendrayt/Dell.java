package com.example.yeshendrayt;

public class Dell implements Brand{
	
	Processors processors;
	
//	public Dell(Processors processors) {
//		this.processors=processors;
//	}
	
	public void setProcessors(Processors processors) {
		this.processors = processors;
	}

	

	@Override
	public void showDetails() {
		System.out.println("You have selected Dell Laptop"+processors.showProcessorDetails());
		
	}

	
}
