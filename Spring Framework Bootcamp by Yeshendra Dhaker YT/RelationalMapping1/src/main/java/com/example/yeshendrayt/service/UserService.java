package com.example.yeshendrayt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.UserDetailsDTO;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.entity.UserDetails;
import com.example.yeshendrayt.repository.UserDetailRepo;
import com.example.yeshendrayt.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserDetailRepo userDetailRepo;

	public User createUser(User user) {

		return userRepo.save(user);
	}

	public UserDetails addUserDetails(Long userid, UserDetailsDTO userDetailsDTO) {

		User user = userRepo.findById(userid)
				.orElseThrow(() -> new RuntimeException("User Not Found for the id " + userid));

		UserDetails userDetails=new UserDetails();
		userDetails.setAddress(userDetailsDTO.getAddress());
		userDetails.setPhone(userDetailsDTO.getPhone());
		userDetails.setPassportNumber(userDetailsDTO.getPassportNumber());
		
		userDetails.setUser(user);
		return userDetailRepo.save(userDetails);
	}

}
