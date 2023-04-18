package com.ivanconsalter.ionicspring.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ivanconsalter.ionicspring.domain.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Long id, EstadoPagamento estadoPagamento, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
		super(id, estadoPagamento, pedido);
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
