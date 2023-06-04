package com.ivanconsalter.ionicspring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.ivanconsalter.ionicspring.domain.Estado;
import com.ivanconsalter.ionicspring.dto.EstadoDTO;

@Component
public class EstadoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoMapper() {
	}

	public EstadoMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public EstadoDTO toDTO(Estado estado) {
		return modelMapper.map(estado, EstadoDTO.class);
	}
	
	public List<EstadoDTO> toListDTO(List<Estado> list) {
		return list.stream().map(this::toDTO).collect(Collectors.toList()) ;
	}
	
	public Page<EstadoDTO> toPageDTO(Page<Estado> page) {
		List<EstadoDTO> estadoDTOList = page.map(this::toDTO).getContent();
		
		return new PageImpl<>(estadoDTOList, page.getPageable(), page.getTotalElements());
	}
	
	public Estado toEntity(EstadoDTO estadoDTO) {
		return modelMapper.map(estadoDTO, Estado.class);
	}

}
