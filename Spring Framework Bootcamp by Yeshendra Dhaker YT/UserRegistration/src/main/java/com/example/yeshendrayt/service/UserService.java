package com.example.yeshendrayt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.entity.SimpleUser;
import com.example.yeshendrayt.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	public void saveUser(SimpleUser simpleUser) {
		userRepo.save(simpleUser);
	}

}
