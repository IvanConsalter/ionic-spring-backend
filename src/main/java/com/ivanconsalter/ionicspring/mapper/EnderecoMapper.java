package com.ivanconsalter.ionicspring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.ivanconsalter.ionicspring.domain.Endereco;
import com.ivanconsalter.ionicspring.dto.EnderecoDTO;

@Component
public class EnderecoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EnderecoMapper() {
	}

	public EnderecoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public EnderecoDTO toDTO(Endereco endereco) {
		return modelMapper.map(endereco, EnderecoDTO.class);
	}
	
	public List<EnderecoDTO> toListDTO(List<Endereco> list) {
		return list.stream().map(this::toDTO).collect(Collectors.toList()) ;
	}
	
	public Page<EnderecoDTO> toPageDTO(Page<Endereco> page) {
		List<EnderecoDTO> enderecoDTOList = page.map(this::toDTO).getContent();
		
		return new PageImpl<>(enderecoDTOList, page.getPageable(), page.getTotalElements());
	}
	
	public Endereco toEntity(EnderecoDTO enderecoDTO) {
		return modelMapper.map(enderecoDTO, Endereco.class);
	}
	
	public List<Endereco> toListEntity(List<EnderecoDTO> list) {
		return list.stream().map(this::toEntity).collect(Collectors.toList()) ;
	}

}
