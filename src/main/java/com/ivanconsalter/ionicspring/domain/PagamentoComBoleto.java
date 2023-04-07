package com.ivanconsalter.ionicspring.domain;

import java.time.LocalDateTime;

import com.ivanconsalter.ionicspring.domain.enums.EstadoPagamento;

public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime dataVencimento;
	
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Long id, EstadoPagamento estadoPagamento, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
		super(id, estadoPagamento);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
}
