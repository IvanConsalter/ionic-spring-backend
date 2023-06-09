package com.ivanconsalter.ionicspring.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.domain.Endereco;
import com.ivanconsalter.ionicspring.domain.ItemPedido;
import com.ivanconsalter.ionicspring.domain.PagamentoComBoleto;
import com.ivanconsalter.ionicspring.domain.Pedido;
import com.ivanconsalter.ionicspring.domain.enums.EstadoPagamento;
import com.ivanconsalter.ionicspring.repositories.EnderecoRepository;
import com.ivanconsalter.ionicspring.repositories.ItemPedidoRepository;
import com.ivanconsalter.ionicspring.repositories.PagamentoRepository;
import com.ivanconsalter.ionicspring.repositories.PedidoRepository;
import com.ivanconsalter.ionicspring.security.UserSecurity;
import com.ivanconsalter.ionicspring.services.exception.AuthorizationException;
import com.ivanconsalter.ionicspring.services.exception.ResourceNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido findById(Long id) {
		return pedidoRepository.findById(id)
				.orElseThrow( 
						() -> new ResourceNotFoundException(
								"Recurso não encontrado. Id: " + id + ", Tipo: " + Cliente.class.getName()
							)
					);
	}
	
	public Page<Pedido> findByPage(Integer page, Integer size, String direction, String orderBy) {
		UserSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Pageable pageable = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		
		Cliente cliente =  clienteService.findById(user.getId());
		return pedidoRepository.findByCliente(cliente, pageable);
	}
	
	public Pedido save(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(LocalDateTime.now());
		pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE.getCodigo());
		pedido.getPagamento().setPedido(pedido);
		
		Endereco endereco = enderecoRepository.findById(pedido.getEnderecoDeEntrega().getId()).get();
		pedido.setEnderecoDeEntrega(endereco);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagtoBoleto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagtoBoleto, pedido.getInstante());
		}
		
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for(ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
		System.out.println(pedido.toString());
		
		return pedido;
		
	}
	

}
