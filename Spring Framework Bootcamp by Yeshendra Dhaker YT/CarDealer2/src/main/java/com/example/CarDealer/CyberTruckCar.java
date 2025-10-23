package com.example.CarDealer;

import org.springframework.stereotype.Component;

@Component("cybertruck")
public class CyberTruckCar implements Car{

	@Override
	public void showCarDetails() {
		System.out.println("This is a Cyber Truck Car");
		
	}

}
