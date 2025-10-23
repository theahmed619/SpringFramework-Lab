package com.example.yeshendrayt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.yeshendrayt.dto.ChangePasswordDTO;
import com.example.yeshendrayt.dto.UserDTO;
import com.example.yeshendrayt.entity.User;
import com.example.yeshendrayt.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDTO getUserById(Long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		return convertToUserDTO(user);
	}

	public UserDTO getUserByUsername(String username) {

		User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		return convertToUserDTO(user);
	}

	public List<UserDTO> getAllUsers() {
		List<User> listOfUser = userRepo.findAll();

		return listOfUser.stream().map(this::convertToUserDTO).collect(Collectors.toList());
	}

	
	
	public UserDTO changePassword(Long id, ChangePasswordDTO changePasswordDTO) {

		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), user.getPassword())) {
			throw new RuntimeException("Current Password is incorrect");
		}

		if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
			throw new RuntimeException("new Password and confirm password does not match");
		}

		user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
		User savedUser = userRepo.save(user);

		return convertToUserDTO(savedUser);

	}

	public UserDTO updateUser(Long id, UserDTO userDTO) {

		User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());

		User savedUser = userRepo.save(user);

		return convertToUserDTO(savedUser);
	}
	
	public void deleteUser(Long id) {
		
		userRepo.deleteById(id);
	}

	public UserDTO convertToUserDTO(User user) {

		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());

		return userDTO;
	}

	

}
