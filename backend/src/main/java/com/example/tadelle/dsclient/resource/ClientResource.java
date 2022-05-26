package com.example.tadelle.dsclient.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tadelle.dsclient.dto.ClientDTO;
import com.example.tadelle.dsclient.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;
	
	@Transactional(readOnly = true)
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

}
