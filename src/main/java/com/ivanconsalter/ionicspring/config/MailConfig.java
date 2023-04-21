package com.ivanconsalter.ionicspring.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean
	public JavaMailSender javaMailSender() {
		
		Properties properties = new Properties();
		
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.connectiontimeout", 10000);
		
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setJavaMailProperties(properties);
		javaMailSenderImpl.setHost("");
		javaMailSenderImpl.setPort(44);
		javaMailSenderImpl.setUsername("");
		javaMailSenderImpl.setPassword("");
		
		return javaMailSenderImpl;
		
	}
	

}
