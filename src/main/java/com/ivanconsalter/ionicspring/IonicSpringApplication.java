package com.ivanconsalter.ionicspring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ivanconsalter.ionicspring.domain.Categoria;
import com.ivanconsalter.ionicspring.domain.Cidade;
import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.domain.Endereco;
import com.ivanconsalter.ionicspring.domain.Estado;
import com.ivanconsalter.ionicspring.domain.Produto;
import com.ivanconsalter.ionicspring.domain.enums.TipoCliente;
import com.ivanconsalter.ionicspring.repositories.CategoriaRepository;
import com.ivanconsalter.ionicspring.repositories.CidadeRepository;
import com.ivanconsalter.ionicspring.repositories.ClienteRepository;
import com.ivanconsalter.ionicspring.repositories.EnderecoRepository;
import com.ivanconsalter.ionicspring.repositories.EstadoRepository;
import com.ivanconsalter.ionicspring.repositories.ProdutoRepository;

@SpringBootApplication
public class IonicSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(IonicSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "Belo Horizonte", estado1);
		Cidade cidade3 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade4 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().add(cidade1);
		estado1.getCidades().add(cidade2);
		
		estado2.getCidades().add(cidade3);
		estado2.getCidades().add(cidade4);
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		
		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "35378912377", TipoCliente.PESSOAFISICA);
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cliente, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", null, "Centro", "38777012", cliente, cidade2);
		
		cliente.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		cliente.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		clienteRepository.save(cliente);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
	}

}
