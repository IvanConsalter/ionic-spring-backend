package com.ivanconsalter.ionicspring.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ivanconsalter.ionicspring.domain.Pedido;
import com.ivanconsalter.ionicspring.services.PedidoService;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		Pedido pedido = pedidoService.findById(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Pedido pedido) {
		pedido = pedidoService.save(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
