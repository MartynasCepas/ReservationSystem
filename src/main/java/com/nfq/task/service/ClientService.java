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
        client.setReservationCode(generateReservationCode(client));
        clientRepository.save(client);
    }

    public String generateReservationCode(Client client){
       String code = String.valueOf(client.getFirstName().charAt(0)) + String.valueOf(client.getLastName().charAt(0)) + String.valueOf(client.getReservationId()) ;
       return code;
    }
}
