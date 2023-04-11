package com.ivanconsalter.ionicspring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;

	private List<EnderecoDTO> enderecos = new ArrayList<>();
	
	private Set<String> telefones = new HashSet<>();
	
	public ClienteInputDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}
	
	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}
	
	public Set<String> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
}
