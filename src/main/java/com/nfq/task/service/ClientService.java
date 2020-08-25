package com.nfq.task.service;

import com.nfq.task.domain.Client;
import com.nfq.task.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createBooking(Client client){
        clientRepository.save(client);
    }
}
