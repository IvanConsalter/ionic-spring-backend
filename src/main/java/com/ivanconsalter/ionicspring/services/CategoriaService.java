package com.ivanconsalter.ionicspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.repositories.CategoriaRepository;
import com.ivanconsalter.ionicspring.resources.exception.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow( 
						() -> new ResourceNotFoundException(
								"Recurso não encontrado. Id: " + id + ", Tipo: " + Categoria.class.getName()
							)
					);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

}
