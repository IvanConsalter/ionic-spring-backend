package com.ivanconsalter.ionicspring.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ivanconsalter.ionicspring.domain.Cliente;
import com.ivanconsalter.ionicspring.dto.ClienteDTO;
import com.ivanconsalter.ionicspring.dto.ClienteInputDTO;
import com.ivanconsalter.ionicspring.services.ClienteService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> listDto = clienteService.findAll(); 
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(path = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "24") Integer size, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
		) {
		Page<ClienteDTO> pageDTO = clienteService.findByPage(page, size, direction, orderBy); 
		return ResponseEntity.ok().body(pageDTO);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> save(@Valid @RequestBody ClienteInputDTO clienteInputDTO) {
		Cliente novoCliente = clienteService.save(clienteInputDTO);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(novoCliente.getId())
				.toUri();

		return ResponseEntity.created(uri).body(novoCliente);
	}
	
	@PostMapping(path = "/imagem")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = clienteService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Long id) {
		ClienteDTO dto = clienteService.update(clienteDto, id);
		return ResponseEntity.ok(dto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
