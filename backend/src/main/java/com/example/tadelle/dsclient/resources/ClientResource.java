package com.example.tadelle.dsclient.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tadelle.dsclient.dto.ClientDTO;
import com.example.tadelle.dsclient.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(
		@RequestParam(value = "page",defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage",defaultValue = "12") Integer linesPerPage,
		@RequestParam(value = "direction",defaultValue = "ASC") String direction,
		@RequestParam(value = "orderBy",defaultValue = "name") String orderBy
			) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		Page<ClientDTO> pageList = clientService.findAll(pageRequest);
		return ResponseEntity.ok().body(pageList);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity <ClientDTO> findById(@PathVariable Long id) {
		ClientDTO clientDto = clientService.findById(id);
		return ResponseEntity.ok().body(clientDto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
		dto = clientService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
}
