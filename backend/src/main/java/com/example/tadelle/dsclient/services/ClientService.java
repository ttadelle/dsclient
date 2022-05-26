package com.example.tadelle.dsclient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tadelle.dsclient.dto.ClientDTO;
import com.example.tadelle.dsclient.entities.Client;
import com.example.tadelle.dsclient.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly=true)
	public Page<ClientDTO> findAll(PageRequest pageRequest) {
		Page<Client> pageList = clientRepository.findAll(pageRequest);
		return pageList.map(x -> (new ClientDTO(x)));
	}

}
