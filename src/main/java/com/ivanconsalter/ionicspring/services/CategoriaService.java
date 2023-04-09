package com.ivanconsalter.ionicspring.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.dto.CategoriaDTO;
import com.ivanconsalter.ionicspring.mapper.CategoriaMapper;
import com.ivanconsalter.ionicspring.repositories.CategoriaRepository;
import com.ivanconsalter.ionicspring.resources.exception.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaMapper categoriaMapper;
	
	public List<CategoriaDTO> findAll() {
		List<Categoria> list = categoriaRepository.findAll();
		
		return categoriaMapper.toListDTO(list);
	}
	
	public Page<CategoriaDTO> findByPage(Integer page, Integer size, String direction, String orderBy) {
		Pageable pageable = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		
		Page<Categoria> listPage = categoriaRepository.findAll(pageable);
		return categoriaMapper.toPageDTO(listPage);
	}
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id)
				.orElseThrow( 
						() -> new ResourceNotFoundException(
								"Recurso não encontrado. Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public CategoriaDTO save(CategoriaDTO categoriaDTO) {
		Categoria categoriaSalva = categoriaRepository.save(categoriaMapper.toEntity(categoriaDTO));
		return categoriaMapper.toDTO(categoriaSalva);
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
