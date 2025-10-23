package com.example.yeshendrayt;

import java.util.Scanner;

public class User {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		System.out.println("Select Drink\n1. LATTE\n2. ESPRESSO\n3. BLACK COFFE");
		int userIntout=scanner.nextInt();
		String selectedDrink="";
		switch(userIntout) {
		case 1:{
			selectedDrink="LATTE";
			break;
		}
		case 2:{
			selectedDrink="ESPRESSO";
			break;
		}
		case 3:{
			selectedDrink="BLACK COFFE";
			break;
		}
		}
		
		Cafeteria drink=Cafeteria.valueOf(selectedDrink);
		System.out.println("Your Drink Price is: "+drink.price);
	
	}

}
