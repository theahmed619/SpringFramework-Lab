package com.example.yeshendrayt;

import org.springframework.stereotype.Component;

@Component("democratic")
public class Democratic implements PoliticalParty {
	
	private String partyName= "Democratic";

	@Override
	public String getPartyName() {
		// TODO Auto-generated method stub
		return this.partyName;
	}

}
