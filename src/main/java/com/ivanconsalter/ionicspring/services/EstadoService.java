package com.ivanconsalter.ionicspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Estado;
import com.ivanconsalter.ionicspring.dto.EstadoDTO;
import com.ivanconsalter.ionicspring.mapper.EstadoMapper;
import com.ivanconsalter.ionicspring.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoMapper estadoMapper;
	
	public List<EstadoDTO> findAllByOrderByNome() {
		List<Estado> list = estadoRepository.findAllByOrderByNome();
		
		return estadoMapper.toListDTO(list);
	}

}
