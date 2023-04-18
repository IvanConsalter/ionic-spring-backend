package com.ivanconsalter.ionicspring.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagtoBoleto, LocalDateTime instanteDoPedido) {
		LocalDateTime dataVencimento = instanteDoPedido.plusDays(7);
		pagtoBoleto.setDataVencimento(dataVencimento);
	}

}
