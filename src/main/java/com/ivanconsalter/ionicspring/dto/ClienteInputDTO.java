package com.ivanconsalter.ionicspring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ivanconsalter.ionicspring.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteInputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Size(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;

	private List<EnderecoDTO> enderecos = new ArrayList<>();
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String telefone1;

	private String telefone2;
	
	private String telefone3;
	
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
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
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
	
	public String getTelefone1() {
		return telefone1;
	}
	
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	
	public String getTelefone2() {
		return telefone2;
	}
	
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	
	public String getTelefone3() {
		return telefone3;
	}
	
	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	
}
