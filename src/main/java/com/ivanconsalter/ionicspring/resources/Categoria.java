package com.ivanconsalter.ionicspring.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/categorias")
public class Categoria {
	
	@GetMapping
	public String listar() {
		return "Testando o REST";
	}

}
