package com.example.yeshendrayt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.LoginRequestDTO;
import com.example.yeshendrayt.dto.LoginResponseDTO;
import com.example.yeshendrayt.dto.RegisterRequestDTO;
import com.example.yeshendrayt.dto.UserDTO;
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
	private JwtService jwtService;


	@Autowired
	private AuthenticationManager authenticationManager;

	public UserDTO registerNormalUser(RegisterRequestDTO registerRequestDTO) {
		if (userRepo.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("Username is already register");
		}

		Set<String> set = new HashSet<>();
		set.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(set);

		User savedUser = userRepo.save(user);

		return convertToUserDTO(user);

	}

	public UserDTO registerAdminUser(RegisterRequestDTO registerRequestDTO) {
		if (userRepo.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
			throw new RuntimeException("Username is already register");
		}

		Set<String> set = new HashSet<>();
		set.add("ROLE_ADMIN");
		set.add("ROLE_USER");

		User user = new User();
		user.setUsername(registerRequestDTO.getUsername());
		user.setEmail(registerRequestDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
		user.setRoles(set);

		User savedUser = userRepo.save(user);

		return convertToUserDTO(savedUser);

	}

	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
		User user = userRepo.findByUsername(loginRequestDTO.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

		String jwtToken = jwtService.generateToken(user);

		return LoginResponseDTO.builder().jwtToken(jwtToken).userDTO(convertToUserDTO(user)).build();

	}

	public ResponseEntity<String> logout() {
		//create a expired cookie
		
		ResponseCookie cookie=ResponseCookie.from("JWT","").httpOnly(true).secure(true)
				.path("/").maxAge(0).sameSite("Strick").build();
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body("LOGGED OUT SUCCESSFULLY");
	}

	public UserDTO convertToUserDTO(User user) {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());

		return userDTO;
	}

}
