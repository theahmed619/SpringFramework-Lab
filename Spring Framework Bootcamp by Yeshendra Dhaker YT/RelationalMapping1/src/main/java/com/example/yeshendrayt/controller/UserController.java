package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.dto.UserDetailsDTO;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.entity.UserDetails;
import com.example.yeshendrayt.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/createuser")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		return ResponseEntity.ok(userService.createUser(user));
	}

	@PostMapping("/{userid}/adduserdetails")
	public ResponseEntity<UserDetails> addUserDetails(@PathVariable Long userid,
			@RequestBody UserDetailsDTO userDetailsDTO) {

		
		return ResponseEntity.ok(userService.addUserDetails(userid, userDetailsDTO));
	}

}
