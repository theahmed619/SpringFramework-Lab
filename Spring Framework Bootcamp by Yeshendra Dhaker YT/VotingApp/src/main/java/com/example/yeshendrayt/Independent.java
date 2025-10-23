package com.example.yeshendrayt;

import org.springframework.stereotype.Component;

@Component("independent")
public class Independent implements PoliticalParty{

	String partyName="Independent";
	
	@Override
	public String getPartyName() {
		// TODO Auto-generated method stub
		return this.partyName;
	}

}
