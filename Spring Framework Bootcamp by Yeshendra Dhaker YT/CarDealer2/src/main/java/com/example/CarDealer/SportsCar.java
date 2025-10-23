package com.example.CarDealer;

import org.springframework.stereotype.Component;

@Component("sports")
public class SportsCar implements Car{

	@Override
	public void showCarDetails() {
		System.out.println("This is a Sports Car");
		
	}

}
