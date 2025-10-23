package com.example.yeshendrayt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.LoginRequestDTO;
import com.example.yeshendrayt.dto.LoginResponseDTO;
import com.example.yeshendrayt.dto.RegisterRequestDTO;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.jwt.JwtService;
import com.example.yeshendrayt.repository.UserRepo;

@Service
public class AuthService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;

	public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {

		if (userRepo.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("User already register");
		}

		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);

		return userRepo.save(user);

	}

	public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {

		if (userRepo.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("User already register");
		}

		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(roles);

		return userRepo.save(user);

	}

	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

		User user = userRepo.findByUsername(loginRequestDTO.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		authenticationManager.authenticate(

  new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

		String token=jwtService.generateToken(user);
		
		return LoginResponseDTO.builder()
				.jwtToken(token)
				.username(user.getUsername())
				.roles(user.getRoles())
				.build();
	}

}
