package com.example.yeshendrayt;

public class SimplePost implements Post {
	
	String message;

	@Override
	public void setMessage(String msg) {
		this.message=msg;
		
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

}
