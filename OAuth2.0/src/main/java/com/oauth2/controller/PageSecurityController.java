package com.oauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.oauth2.entity.UserLoginDetails;
import com.oauth2.service.UserLoginService;

@RestController
public class PageSecurityController {

	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping("/addlogin")
	public void addLoginDerails(@RequestBody UserLoginDetails userLoginDetails) {
		userLoginService.saveUserLoginDetails(userLoginDetails);
	}
	
	@GetMapping("/login")
	public String forAdmin() {
		return "Welcome All";
	}
	
}
