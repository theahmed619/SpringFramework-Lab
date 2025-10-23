package com.example.yeshendrayt.service;

import java.util.HashSet;
import java.util.Set;

import com.example.yeshendrayt.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.LoginRequestDTO;
import com.example.yeshendrayt.dto.LoginResponseDTO;
import com.example.yeshendrayt.dto.RegisterDTO;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.repository.UserRepo;

@Service
public class AuthService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public User registerNormalUser(RegisterDTO registerDTO) {
		if (userRepo.findByUsername(registerDTO.getUsername()).isPresent()) {
			throw new RuntimeException("User Already registered");
		}

		Set<String> roles = new HashSet<>();
		roles.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerDTO.getUsername());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		user.setRoles(roles);

		return userRepo.save(user);
	}

	public User registerAdminUser(RegisterDTO registerDTO) {

		if (userRepo.findByUsername(registerDTO.getUsername()).isPresent()) {
			throw new RuntimeException("User Already registered");
		}

		Set<String> roles = new HashSet<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerDTO.getUsername());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		user.setRoles(roles);

		return userRepo.save(user);
	}

	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
		User user = userRepo.findByUsername(loginRequestDTO.getUsername())
				.orElseThrow(() -> new RuntimeException("User Not Found "));
		
		String token = jwtService.generateToken(user);
		
		return LoginResponseDTO.builder()
				.token(token)
				.username(user.getUsername())
				.roles(user.getRoles())
				.build();

	}

}
