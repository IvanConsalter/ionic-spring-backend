package com.ivanconsalter.ionicspring.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.dto.CategoriaDTO;
import com.ivanconsalter.ionicspring.services.CategoriaService;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> listDto = categoriaService.findAll(); 
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(path = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "24") Integer size, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
		) {
		Page<CategoriaDTO> pageDTO = categoriaService.findByPage(page, size, direction, orderBy); 
		return ResponseEntity.ok().body(pageDTO);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria categoria = categoriaService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.save(categoria);
		URI uri = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(categoriaSalva.getId())
						.toUri();
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria, @PathVariable Long id) {
		Categoria categoriaAtualizada = categoriaService.update(categoria, id);
		
		return ResponseEntity.ok(categoriaAtualizada);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
