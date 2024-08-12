package com.dev.HiddenBATH.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.HiddenBATH.model.Client;
import com.dev.HiddenBATH.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;
	
	public void clientInsert(Client client) {
		client.setJoinDate(new Date());
		client.setCheckDate(new Date());
		client.setContact(false);
		
		clientRepository.save(client);
	}
}
