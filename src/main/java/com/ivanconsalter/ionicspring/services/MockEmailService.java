package com.ivanconsalter.ionicspring.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockEmailService extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(MimeMessage mailSender) {
		LOG.info("Simulando envio de email...");
		LOG.info(mailSender.toString());
		LOG.info("Email enviado");
	}

}
