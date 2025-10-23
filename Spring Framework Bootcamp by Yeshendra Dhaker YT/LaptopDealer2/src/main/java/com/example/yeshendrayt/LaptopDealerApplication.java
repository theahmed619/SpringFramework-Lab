package com.example.yeshendrayt;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class LaptopDealerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LaptopDealerApplication.class, args);
		
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext("com.example.yeshendrayt");
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Choose the Brand you wanna buy: \n1. Dell\n2. MacBook\n3. Microsoft");
		
		int userBrand=scanner.nextInt();
		
		System.out.println("Choose the Processor: \n1. i3\n2. i5\n3.i7");
		
		int userProccessor=scanner.nextInt();
		
		
		switch(userBrand) {
		case 1: {
			Dell dell=(Dell)context.getBean("dell");
			switch (userProccessor) {
					case 1: {
						dell.processors=(Processors) context.getBean("i3");
						break;
					}
					case 2:{
						dell.processors=(Processors) context.getBean("i5");
						break;
					}
					case 3:{
						dell.processors=(Processors) context.getBean("i7");
						break;
					}
			
			}
			dell.showDetails();
			break;
			
		}
		 case 2: { // MacBook
             MacBook macBook = (MacBook) context.getBean("macBook");

        

             switch (userProccessor) {
                 case 1:
                     macBook.processors = (Processors) context.getBean("i3");
                     break;
                 case 2:
                     macBook.processors = (Processors) context.getBean("i5");
                     break;
                 case 3:
                     macBook.processors = (Processors) context.getBean("i7");
                     break;
                 default:
                     System.out.println("Invalid processor choice for MacBook.");
                     return;
             }
             macBook.showDetails();
             break;
		 }
		 case 3: { // Microsoft
             Microsoft microsoft = (Microsoft) context.getBean("microsoft");

             switch (userProccessor) {
                 case 1:
                     microsoft.processors = (Processors) context.getBean("i3");
                     break;
                 case 2:
                     microsoft.processors = (Processors) context.getBean("i5");
                     break;
                 case 3:
                     microsoft.processors = (Processors) context.getBean("i7");
                     break;
                 default:
                     System.out.println("Invalid processor choice for Microsoft.");
                    
             }
             microsoft.showDetails();
             break;
         }
		 
		 
		}
		
		
		scanner.close();
        context.close();
	}

}
