package com.ivanconsalter.ionicspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.domain.Pedido;
import com.ivanconsalter.ionicspring.repositories.PedidoRepository;
import com.ivanconsalter.ionicspring.services.exception.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido findById(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow( 
						() -> new ResourceNotFoundException(
								"Recurso não encontrado. Id: " + id + ", Tipo: " + Cliente.class.getName()
							)
					);
	}
	

}
