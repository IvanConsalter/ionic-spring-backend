package com.ivanconsalter.ionicspring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.dto.CategoriaDTO;
import com.ivanconsalter.ionicspring.repositories.CategoriaRepository;
import com.ivanconsalter.ionicspring.resources.exception.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CategoriaDTO> findAll() {
		List<Categoria> list = categoriaRepository.findAll();
		
		return list.stream().map( (categoria) -> modelMapper.map(categoria, CategoriaDTO.class)).collect(Collectors.toList());
	}
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow( 
						() -> new ResourceNotFoundException(
								"Recurso não encontrado. Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Categoria categoriaAtualizada, Long id) {
		Categoria categoriaAntesAtualizar = findById(id);
		
		BeanUtils.copyProperties(categoriaAtualizada, categoriaAntesAtualizar, "id");

		return categoriaRepository.save(categoriaAtualizada);
	}
	
	public void delete(Long id) {
		findById(id);
		
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possível excluir uma categoria que possui produtos");
		}
	}

}
