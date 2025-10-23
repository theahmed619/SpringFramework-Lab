package com.example.CarDealer;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class CarDealerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CarDealerApplication.class, args);
		
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Scanner scanner=new Scanner(System.in);
		System.out.println("Choose the Car you wanna buy: \n1. Family Car\n2. Sports Car\n3. Cyber Truck Car\n");
		int userSelect =scanner.nextInt();
		String beanId="";
		switch (userSelect) {
		case 1: beanId="family";
		break;
		case 2: beanId="sports";
		break;
		case 3: beanId="cybertruck";
		
	}
		Car car=(Car)context.getBean(beanId);
		car.showCarDetails();
		

}
}