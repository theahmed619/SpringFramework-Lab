package com.example.yeshendrayt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
	
	private Long id;
	
	@NotBlank
	@Size(min=3, max=50)
	private String username;
	
	@NotBlank
	@Size(max=7)
	@Email
	private String email;

	private Set<String> roles;

}
