package com.ivanconsalter.ionicspring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.ivanconsalter.ionicspring.domain.Produto;
import com.ivanconsalter.ionicspring.dto.ProdutoDTO;

@Component
public class ProdutoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoMapper() {
	}

	public ProdutoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public ProdutoDTO toDTO(Produto produto) {
		return modelMapper.map(produto, ProdutoDTO.class);
	}
	
	public List<ProdutoDTO> toListDTO(List<Produto> list) {
		return list.stream().map(this::toDTO).collect(Collectors.toList()) ;
	}
	
	public Page<ProdutoDTO> toPageDTO(Page<Produto> page) {
		List<ProdutoDTO> produtoDTOList = page.map(this::toDTO).getContent();
		
		return new PageImpl<>(produtoDTOList, page.getPageable(), page.getTotalElements());
	}
	
	public Produto toEntity(ProdutoDTO produtoDTO) {
		return modelMapper.map(produtoDTO, Produto.class);
	}
	
}
