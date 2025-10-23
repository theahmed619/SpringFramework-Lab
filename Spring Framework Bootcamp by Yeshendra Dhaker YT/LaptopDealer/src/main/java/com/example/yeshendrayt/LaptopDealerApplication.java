package com.example.yeshendrayt;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class LaptopDealerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LaptopDealerApplication.class, args);
		
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Choose the Brand you wanna buy: \n1. Dell\n2. MacBook\n3. Microsoft");
		
		int userBrand=scanner.nextInt();
		
		System.out.println("Choose the Processor: \n1. i3\n2. i5\n3.i7");
		
		int userProccessor=scanner.nextInt();
		
		String beanId="";
		
		switch(userBrand) {
		case 1: {
			
			switch (userProccessor) {
					case 1: {
						beanId="dellwithi3";
						break;
					}
					case 2:{
						beanId="dellwithi5";
						break;
					}
					case 3:{
						beanId="dellwithi7";
						break;
					}
			
			}
			break;
			
		}
		case 2:{
			
			
			break;
		}
		case 3:{
			
			break;
		}
		}
		
		Brand brand=(Brand)context.getBean(beanId);
		brand.showDetails();
		
	}

}
