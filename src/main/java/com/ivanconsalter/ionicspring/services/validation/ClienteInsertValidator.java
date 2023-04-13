package com.ivanconsalter.ionicspring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.domain.enums.TipoCliente;
import com.ivanconsalter.ionicspring.dto.ClienteInputDTO;
import com.ivanconsalter.ionicspring.repositories.ClienteRepository;
import com.ivanconsalter.ionicspring.resources.exception.FieldMessage;
import com.ivanconsalter.ionicspring.services.validation.utils.ValidCpfCnpj;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteInputDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	@Override
	public boolean isValid(ClienteInputDTO clienteInputDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(clienteInputDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !ValidCpfCnpj.isValidCPF(clienteInputDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(clienteInputDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !ValidCpfCnpj.isValidCNPJ(clienteInputDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente cliente = clienteRepository.findByEmail(clienteInputDTO.getEmail());
		if(cliente != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

	
}
