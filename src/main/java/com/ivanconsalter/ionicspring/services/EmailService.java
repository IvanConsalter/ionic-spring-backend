package com.ivanconsalter.ionicspring.services;

import javax.mail.internet.MimeMessage;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(MimeMessage mailSender);
	
	void sendNewPasswordEmail(Cliente cliente, String newPassword);

}
