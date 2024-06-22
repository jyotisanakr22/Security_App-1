package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.security.entity.User;
import com.security.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//http:localhost:8080/login
	@RequestMapping("/login")
	public String loginPage() {
		return "login/login";
	}
	
	@RequestMapping("/logout-success")
	public String logoutPage() {
		return "login/logout";
	}
	
	//http://localhost:8080/showReg
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		return "login/registerUser"; //Remember "registerUser" is under "login" folder...
	}
	
	@RequestMapping("/registerUser")
	public String registerUser(@ModelAttribute("user")User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword())); //It will convert the original password to some 16 digit password...
		userService.saveUser(user);
		return "login/registerUser";
	}
}
