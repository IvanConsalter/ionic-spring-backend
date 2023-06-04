package com.ivanconsalter.ionicspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanconsalter.ionicspring.domain.Cidade;
import com.ivanconsalter.ionicspring.dto.CidadeDTO;
import com.ivanconsalter.ionicspring.mapper.CidadeMapper;
import com.ivanconsalter.ionicspring.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeMapper cidadeMapper;
	
	public List<CidadeDTO> findByEstado(Long estadoId) {
		List<Cidade> list = cidadeRepository.findByEstado(estadoId);
		
		return cidadeMapper.toListDTO(list);
	}

}
