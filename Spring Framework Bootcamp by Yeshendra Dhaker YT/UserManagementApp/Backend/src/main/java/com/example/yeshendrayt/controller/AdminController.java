package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.dto.RegisterRequestDTO;
import com.example.yeshendrayt.dto.UserDTO;
import com.example.yeshendrayt.service.AuthService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/registeradminuser")
	public ResponseEntity<UserDTO> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.ok(authService.registerAdminUser(registerRequestDTO));

	}

}
