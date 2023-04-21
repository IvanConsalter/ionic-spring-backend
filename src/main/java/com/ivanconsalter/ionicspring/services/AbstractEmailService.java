package com.ivanconsalter.ionicspring.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ivanconsalter.ionicspring.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendOrderConfirmationEmail(Pedido pedido) {
		MimeMessage mailSender = preprareSimpleMailMessageFromPedido(pedido);
		sendEmail(mailSender);
	}
	
	protected MimeMessage preprareSimpleMailMessageFromPedido(Pedido pedido) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(pedido.getCliente().getEmail());
			mimeMessageHelper.setSubject("Pedido confirmado! Código: " + pedido.getId());
			mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
			mimeMessageHelper.setText(pedido.toString());

			return mimeMessage;
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e);
		}
	}
}
