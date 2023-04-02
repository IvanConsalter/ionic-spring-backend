package com.ivanconsalter.ionicspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).get();
	}

}
