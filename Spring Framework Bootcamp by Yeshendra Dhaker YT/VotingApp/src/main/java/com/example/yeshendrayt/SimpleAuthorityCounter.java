package com.example.yeshendrayt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component("authcounter")
public class SimpleAuthorityCounter implements AuthorityCounter {
	
	@PostConstruct
	public void init() {
		System.out.println("DB Connection Successfull");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Voting has been closed");
	}
	
	@Autowired
	private UserList userList;

//	@Override
//	public void setUserList(UserList userList) {
//		this.userList=userList;
//		
//	}

	@Override
	public UserList getUserList() {
		// TODO Auto-generated method stub
		return this.userList;
	}

}
