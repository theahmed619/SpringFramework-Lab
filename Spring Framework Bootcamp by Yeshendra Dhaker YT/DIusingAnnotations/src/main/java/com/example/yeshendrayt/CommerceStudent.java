package com.example.yeshendrayt;

import org.springframework.stereotype.Component;

@Component("commerce")
public class CommerceStudent implements Student {

	@Override
	public String stream() {
		// TODO Auto-generated method stub
		return "im commerce student";
	}

}
