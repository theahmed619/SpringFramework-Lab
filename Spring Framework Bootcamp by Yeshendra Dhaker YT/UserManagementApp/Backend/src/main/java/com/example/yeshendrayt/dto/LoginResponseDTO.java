package com.example.yeshendrayt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
	
	private String jwtToken;
	private UserDTO userDTO;
	
	

}
