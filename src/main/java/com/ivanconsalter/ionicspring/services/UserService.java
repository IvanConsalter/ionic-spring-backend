package com.ivanconsalter.ionicspring.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ivanconsalter.ionicspring.security.UserSecurity;

public class UserService {
	
	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}
