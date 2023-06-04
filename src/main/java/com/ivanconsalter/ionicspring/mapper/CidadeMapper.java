package com.ivanconsalter.ionicspring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.ivanconsalter.ionicspring.domain.Cidade;
import com.ivanconsalter.ionicspring.dto.CidadeDTO;

@Component
public class CidadeMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CidadeMapper() {
	}

	public CidadeMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public CidadeDTO toDTO(Cidade cidade) {
		return modelMapper.map(cidade, CidadeDTO.class);
	}
	
	public List<CidadeDTO> toListDTO(List<Cidade> list) {
		return list.stream().map(this::toDTO).collect(Collectors.toList()) ;
	}
	
	public Page<CidadeDTO> toPageDTO(Page<Cidade> page) {
		List<CidadeDTO> cidadeDTOList = page.map(this::toDTO).getContent();
		
		return new PageImpl<>(cidadeDTOList, page.getPageable(), page.getTotalElements());
	}
	
	public Cidade toEntity(CidadeDTO cidadeDTO) {
		return modelMapper.map(cidadeDTO, Cidade.class);
	}

}
