package com.example.yeshendrayt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.yeshendrayt.entity.SimpleUser;
import com.example.yeshendrayt.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/")
	public String showRegForm(Model model) {
		model.addAttribute("simpleuser",new SimpleUser());
		return"userreg";
	}

	@RequestMapping("/register")
	public String regUser(@ModelAttribute("simpleuser") @Valid SimpleUser simpleUser, BindingResult result) {
		if(result.hasErrors()) {
			return "userreg";
		}
		
		userService.saveUser(simpleUser);
		
		return"success";
		
	}
}
