package com.example.yeshendrayt;

import org.springframework.stereotype.Component;

@Component("republic")
public class Republic implements PoliticalParty{
	
	String partyName="Republic";

	@Override
	public String getPartyName() {
		// TODO Auto-generated method stub
		return this.partyName;
	}

}
