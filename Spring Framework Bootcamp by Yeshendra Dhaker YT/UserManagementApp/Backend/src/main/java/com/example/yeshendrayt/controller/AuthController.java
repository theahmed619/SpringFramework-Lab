package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yeshendrayt.dto.LoginRequestDTO;
import com.example.yeshendrayt.dto.LoginResponseDTO;
import com.example.yeshendrayt.dto.RegisterRequestDTO;
import com.example.yeshendrayt.dto.UserDTO;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.repository.UserRepo;
import com.example.yeshendrayt.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/registernormaluser")
	public ResponseEntity<UserDTO> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.ok(authService.registerNormalUser(registerRequestDTO));

	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);

		ResponseCookie cookie = ResponseCookie.from("JWT", loginResponseDTO.getJwtToken()).httpOnly(true).secure(true)
				.path("/").maxAge(1 * 60 * 60)// 1 hour
				.sameSite("Strick").build();



		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(loginResponseDTO.getUserDTO());
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		return authService.logout();
	}

	@GetMapping("/getcurrentuser")
	public ResponseEntity<?> getCurrentUser(Authentication authentication) {

		if (authentication == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
		}

		String username = authentication.getName();
		User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		return ResponseEntity.ok(convertToUserDTO(user));
	}

	public UserDTO convertToUserDTO(User user) {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		userDTO.setRoles(user.getRoles());

		return userDTO;
	}

}
