package com.ivanconsalter.ionicspring.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.services.CategoriaService;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1L, "Informática");
		Categoria cat2 = new Categoria(2L, "Escritório");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;  
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}

}
