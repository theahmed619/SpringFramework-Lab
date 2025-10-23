package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.dto.LoginRequestDTO;
import com.example.yeshendrayt.dto.LoginResponseDTO;
import com.example.yeshendrayt.dto.RegisterRequestDTO;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/registernormaluser")
	public ResponseEntity<User> regiterNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {

		return ResponseEntity.ok(authService.registerNormalUser(registerRequestDTO));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
		
		return ResponseEntity.ok(authService.login(loginRequestDTO));
		
	}
	
	
}
