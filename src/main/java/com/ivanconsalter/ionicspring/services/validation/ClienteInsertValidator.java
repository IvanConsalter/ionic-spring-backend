package com.ivanconsalter.ionicspring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ivanconsalter.ionicspring.domain.enums.TipoCliente;
import com.ivanconsalter.ionicspring.dto.ClienteInputDTO;
import com.ivanconsalter.ionicspring.resources.exception.FieldMessage;
import com.ivanconsalter.ionicspring.services.validation.utils.ValidCpfCnpj;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteInputDTO> {

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
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

	
}
