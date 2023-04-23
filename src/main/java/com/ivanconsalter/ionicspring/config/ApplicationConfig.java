package com.ivanconsalter.ionicspring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/config.properties")
public class ApplicationConfig {
	
	@Value("${ionic-spring.mail.host}")
	private String mailHost;
	
	@Value("${ionic-spring.mail.port}")
	private Integer mailPort;
	
	@Value("${ionic-spring.mail.username}")
	private String mailUsername;
	
	@Value("${ionic-spring.mail.password}")
	private String mailPassword;

}
