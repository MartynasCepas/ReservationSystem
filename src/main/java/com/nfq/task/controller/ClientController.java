package com.nfq.task.controller;

import com.nfq.task.domain.Client;
import com.nfq.task.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Client client, BindingResult result){

        clientService.createBooking(client);

        return new ResponseEntity<>("Booking was created successfully", HttpStatus.CREATED);
    }
}
