package com.ivanconsalter.ionicspring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.dto.ClienteDTO;
import com.ivanconsalter.ionicspring.dto.ClienteInputDTO;

@Component
public class ClienteMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteMapper() {
	}

	public ClienteMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public ClienteDTO toDTO(Cliente cliente) {
		return modelMapper.map(cliente, ClienteDTO.class);
	}
	
	public List<ClienteDTO> toListDTO(List<Cliente> list) {
		return list.stream().map(this::toDTO).collect(Collectors.toList()) ;
	}
	
	public Page<ClienteDTO> toPageDTO(Page<Cliente> page) {
		List<ClienteDTO> clienteDTOList = page.map(this::toDTO).getContent();
		
		return new PageImpl<>(clienteDTOList, page.getPageable(), page.getTotalElements());
	}
	
	public Cliente toEntity(ClienteDTO clienteDTO) {
		return modelMapper.map(clienteDTO, Cliente.class);
	}
	
	public Cliente fromClienteInputDTOtoEntity(ClienteInputDTO clienteInputDTO) {
		return modelMapper.map(clienteInputDTO, Cliente.class);
	}

}
