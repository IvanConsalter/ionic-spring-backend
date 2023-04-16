package com.ivanconsalter.ionicspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanconsalter.ionicspring.domain.Produto;
import com.ivanconsalter.ionicspring.services.ProdutoService;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService; 
	
	@GetMapping
	public List<Produto> findAll() {
		return produtoService.findAll();
	}
}
