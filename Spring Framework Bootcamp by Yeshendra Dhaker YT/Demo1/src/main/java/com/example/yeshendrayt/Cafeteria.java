package com.example.yeshendrayt;

public enum Cafeteria {
	
	LATTE(70),
	ESPRESSO(60),
	BLACK_COFFEE(45);
	
	int price;
	
	Cafeteria(int price){
		this.price=price;
	}

}
