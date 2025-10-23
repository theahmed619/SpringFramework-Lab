package com.example.yeshendrayt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class SimpleUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Username is Required")
	@Size(min = 2, max=20, message = "Username must be between 2 and 20 characters")
	private String userName;
	
	@NotNull(message = "Password is required")
	@Pattern(regexp = ".*[A-Z].*", message = "Password should contain at least 1 uppercase character")
	@Pattern(regexp = ".*[a-z].*", message = "Password should contain at least one lowercase")
	@Pattern(regexp = ".*\\d.*", message = "Password should contain at least one number")
	private String password;
	
	@NotNull(message = "Email is required")
	private String email;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
