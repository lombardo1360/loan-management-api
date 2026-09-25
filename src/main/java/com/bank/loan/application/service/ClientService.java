package com.bank.loan.application.service;

import com.bank.loan.domain.model.Client;
import com.bank.loan.application.dto.ClientResponse;
import com.bank.loan.application.dto.CreateClientRequest;
import com.bank.loan.domain.exception.ClientNotFoundException;
import com.bank.loan.infrastructure.persistence.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client createClient(CreateClientRequest clientRequest){

        Client client = new Client(
                clientRequest.getDocumentNumber(),
                clientRequest.getFirstName(),
                clientRequest.getLastName(),
                clientRequest.getEmail(),
                clientRequest.getPhone(),
                clientRequest.getBirthDate()

        );
        return clientRepository.save(client);
    };

    public ClientResponse getClientById(Long id) {

        Client client = clientRepository
                .findById(id)
                .orElseThrow(() ->
                        new ClientNotFoundException(
                                "Client not found with id: " + id
                        )
                );

        return new ClientResponse(
                client.getId(),
                client.getDocumentNumber(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhone(),
                client.getBirthDate()
        );
    }
}
