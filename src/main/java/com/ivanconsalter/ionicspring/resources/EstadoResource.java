package com.ivanconsalter.ionicspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanconsalter.ionicspring.dto.CidadeDTO;
import com.ivanconsalter.ionicspring.dto.EstadoDTO;
import com.ivanconsalter.ionicspring.services.CidadeService;
import com.ivanconsalter.ionicspring.services.EstadoService;

@RestController
@RequestMapping(path = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	@GetMapping()
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> listDTO = estadoService.findAllByOrderByNome();  
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(path = "/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidadesByEstado(@PathVariable Long estadoId) {
		List<CidadeDTO> listDTO = cidadeService.findByEstado(estadoId);  
		return ResponseEntity.ok().body(listDTO);
	}

}
