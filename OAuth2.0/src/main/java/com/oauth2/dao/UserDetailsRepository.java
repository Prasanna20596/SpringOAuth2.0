package com.oauth2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.oauth2.entity.UserLoginDetails;

public interface UserDetailsRepository extends JpaRepository<UserLoginDetails, Integer>{
	
	UserLoginDetails findByUsername(String username);
	
}