package com.ivanconsalter.ionicspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivanconsalter.ionicspring.domain.Produto;
import com.ivanconsalter.ionicspring.dto.ProdutoDTO;
import com.ivanconsalter.ionicspring.resources.utils.URL;
import com.ivanconsalter.ionicspring.services.ProdutoService;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService; 
	
//	@GetMapping
//	public List<Produto> findAll() {
//		return produtoService.findAll();
//	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Produto produto = produtoService.findById(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> search(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "idCategorias", defaultValue = "") String idCategorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "24") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Long> ids = URL.decodeIntList(idCategorias);
		Page<ProdutoDTO> pageProdutoDto = produtoService.search(nomeDecoded, ids, page, size, direction, orderBy);
		
		return ResponseEntity.ok().body(pageProdutoDto);
	}
	
	
}
