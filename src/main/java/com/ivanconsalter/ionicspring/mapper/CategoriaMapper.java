package com.ivanconsalter.ionicspring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.dto.CategoriaDTO;

@Component
public class CategoriaMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CategoriaMapper() {
	}

	public CategoriaMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public CategoriaDTO toDTO(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaDTO.class);
	}
	
	public List<CategoriaDTO> toListDTO(List<Categoria> list) {
		return list.stream().map(this::toDTO).collect(Collectors.toList()) ;
	}
	
	public Page<CategoriaDTO> toPageDTO(Page<Categoria> page) {
		List<CategoriaDTO> categoriaDTOList = page.map(this::toDTO).getContent();
		
		return new PageImpl<>(categoriaDTOList, page.getPageable(), page.getTotalElements());
	}
	
	public Categoria toEntity(CategoriaDTO categoriaDTO) {
		return modelMapper.map(categoriaDTO, Categoria.class);
	}

}
