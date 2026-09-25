package com.bank.loan.api.controller;

import com.bank.loan.domain.model.Client;
import com.bank.loan.application.dto.ClientResponse;
import com.bank.loan.application.dto.CreateClientRequest;
import com.bank.loan.application.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody CreateClientRequest request) {

        Client client = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable("id") Long id){
        ClientResponse client = clientService.getClientById(id);

        return ResponseEntity.ok(client);
    }
}
