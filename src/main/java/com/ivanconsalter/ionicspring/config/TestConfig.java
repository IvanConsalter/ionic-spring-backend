package com.ivanconsalter.ionicspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ivanconsalter.ionicspring.services.DBService;
import com.ivanconsalter.ionicspring.services.EmailService;
import com.ivanconsalter.ionicspring.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}
	
//	@Bean
//	public EmailService emailService() {
//		return new MockEmailService();
//	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
