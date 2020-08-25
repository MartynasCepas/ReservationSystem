package com.nfq.task.controller;

import com.nfq.task.domain.Client;
import com.nfq.task.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/booking/create")
    public String createBooking(Model model) {
        model.addAttribute("client", new Client());
        return "booking";
    }

    @PostMapping("/booking/create")
    public String createBooking(@ModelAttribute Client client, Model model) {
        clientService.createBooking(client);
        model.addAttribute("client", client);
        return "result";
    }
}
