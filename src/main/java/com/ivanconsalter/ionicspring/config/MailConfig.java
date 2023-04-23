package com.ivanconsalter.ionicspring.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ivanconsalter.ionicspring.config.property.IonicSpringProperty;

@Configuration
public class MailConfig {
	
	@Autowired
	private IonicSpringProperty ionicSpringProperty;
	
	@Bean
	public JavaMailSender javaMailSender() {
		
		Properties properties = new Properties();
		
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.connectiontimeout", 10000);
		
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setJavaMailProperties(properties);
		javaMailSenderImpl.setHost(ionicSpringProperty.getMail().getHost());
		javaMailSenderImpl.setPort(ionicSpringProperty.getMail().getPort());
		javaMailSenderImpl.setUsername(ionicSpringProperty.getMail().getUsername());
		javaMailSenderImpl.setPassword(ionicSpringProperty.getMail().getPassword());
		
		return javaMailSenderImpl;
		
	}
	

}
