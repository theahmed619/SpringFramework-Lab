package com.example.CarDealer;

import org.springframework.stereotype.Component;

@Component("family")
public class FamilyCar implements Car{

	@Override
	public void showCarDetails() {
		System.out.println("This is a Family Car");
		
	}

}
