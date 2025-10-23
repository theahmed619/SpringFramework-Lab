package com.example.yeshendrayt;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("user")
@Scope("prototype")
public class SimpleUser implements User{
	
	private String userName;
	
	private PoliticalParty politicalParty;

	@Override
	public void setUserName(String userName) {
		this.userName=userName;
		
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public void setPoliticalParty(PoliticalParty politicalParty) {
		this.politicalParty=politicalParty;
		
	}

	@Override
	public PoliticalParty getPoliticalParty() {
		return this.politicalParty;
	}

}
