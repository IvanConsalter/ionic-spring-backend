package com.ivanconsalter.ionicspring.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ivanconsalter.ionicspring.config.property.IonicSpringProperty;
import com.ivanconsalter.ionicspring.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Autowired
	private IonicSpringProperty ionicSpringProperty;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public void sendOrderConfirmationEmail(Pedido pedido) {
		MimeMessage mailSender;
		try {
			mailSender = preprareSimpleMailMessageFromPedido(pedido, true);
			sendEmail(mailSender);
		} catch (MessagingException e) {
			e.printStackTrace();
			try {
				mailSender = preprareSimpleMailMessageFromPedido(pedido, false);
				sendEmail(mailSender);
			} catch (MessagingException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public void sendOrderConfirmationTemplateHtmlEmail(Pedido pedido) {
		
	}
	
	protected MimeMessage preprareSimpleMailMessageFromPedido(Pedido pedido, Boolean isHtmlTemplate) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
		mimeMessageHelper.setFrom(ionicSpringProperty.getMail().getHost());
		mimeMessageHelper.setTo(pedido.getCliente().getEmail());
		mimeMessageHelper.setSubject("Pedido confirmado! Código: " + pedido.getId());
		mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
		
		if(isHtmlTemplate) {
			mimeMessageHelper.setText(htmlFromTemplatePedido(pedido), true);
		} else {
			mimeMessageHelper.setText(pedido.toString());
		}

		return mimeMessage;
		
	}
	
	protected String htmlFromTemplatePedido(Pedido pedido) {
		Context context = new Context();
		context.setVariable("pedido", pedido);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
}
