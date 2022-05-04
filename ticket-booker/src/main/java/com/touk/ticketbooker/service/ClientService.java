package com.touk.ticketbooker.service;

import com.touk.ticketbooker.exception.ClientNotFoundException;
import com.touk.ticketbooker.model.Client;
import com.touk.ticketbooker.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    // getsert <=> get or - if does not exist - insert new
    public Client getsertClientWithNameAndSurname(String name, String surname) {
        try {
            return getClientByNameAndSurname(name, surname);
        }
        catch (ClientNotFoundException cnfe) {
            return clientRepository.save(
                    Client.builder()
                            .name(name)
                            .surname(surname)
                            .build()
            );
        }
    }

    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(
                () -> new ClientNotFoundException(clientId, "Client with ID = " + clientId + " was not found!")
        );
    }

    public Client getClientByNameAndSurname(String name, String surname) {
        return clientRepository.findByNameAndSurname(name, surname).orElseThrow(
                () -> new ClientNotFoundException(null, "Client '" + name + " " + surname + "' was not found!")
        );
    }
}
