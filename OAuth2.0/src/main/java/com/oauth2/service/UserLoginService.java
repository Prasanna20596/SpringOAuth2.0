package com.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.oauth2.dao.UserDetailsRepository;
import com.oauth2.entity.UserLoginDetails;

@Service
public class UserLoginService {
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	public UserLoginDetails saveUserLoginDetails(UserLoginDetails userLoginDetails) {
		userLoginDetails.setPassword(new BCryptPasswordEncoder().encode(userLoginDetails.getPassword()));
		return userDetailsRepository.save(userLoginDetails);
	}
	

}
