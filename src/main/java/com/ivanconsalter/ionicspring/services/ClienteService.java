package com.ivanconsalter.ionicspring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.domain.Endereco;
import com.ivanconsalter.ionicspring.dto.ClienteDTO;
import com.ivanconsalter.ionicspring.dto.ClienteInputDTO;
import com.ivanconsalter.ionicspring.dto.EnderecoDTO;
import com.ivanconsalter.ionicspring.mapper.ClienteMapper;
import com.ivanconsalter.ionicspring.mapper.EnderecoMapper;
import com.ivanconsalter.ionicspring.repositories.ClienteRepository;
import com.ivanconsalter.ionicspring.repositories.EnderecoRepository;
import com.ivanconsalter.ionicspring.services.exception.DataIntegrityException;
import com.ivanconsalter.ionicspring.services.exception.ResourceNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EnderecoMapper enderecoMapper;
	
	public List<ClienteDTO> findAll() {
		List<Cliente> list = clienteRepository.findAll();
		
		return clienteMapper.toListDTO(list);
	}
	
	public Page<ClienteDTO> findByPage(Integer page, Integer size, String direction, String orderBy) {
		Pageable pageable = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		
		Page<Cliente> listPage = clienteRepository.findAll(pageable);
		return clienteMapper.toPageDTO(listPage);
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow( 
						() -> new ResourceNotFoundException(
								"Recurso não encontrado. Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente save(ClienteInputDTO clienteInputDTO) {
		Cliente novoCliente = clienteMapper.fromClienteInputDTOtoEntity(clienteInputDTO);
		
		Cliente clienteSalvo = clienteRepository.save(novoCliente);
		
		List<EnderecoDTO> enderecosDto = new ArrayList<>();
		
		for( EnderecoDTO enderecoDto : clienteInputDTO.getEnderecos()) {
			enderecoDto.setClienteId(clienteSalvo.getId());
			enderecosDto.add(enderecoDto);
		}
		
		List<Endereco> enderecos = enderecoMapper.toListEntity(enderecosDto);
		enderecoRepository.saveAll(enderecos);
		
		return findById(novoCliente.getId());
	}
	
	public Cliente update(Cliente clienteAtualizada, Long id) {
		Cliente clienteAntesAtualizar = findById(id);
		
		BeanUtils.copyProperties(clienteAtualizada, clienteAntesAtualizar, "id");

		return clienteRepository.save(clienteAtualizada);
	}
	
	public void delete(Long id) {
		findById(id);
		
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não foi possível excluir um cliente que possui pedidos relacionados");
		}
	}

}
