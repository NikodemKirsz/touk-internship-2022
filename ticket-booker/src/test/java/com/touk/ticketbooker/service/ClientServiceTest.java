package com.touk.ticketbooker.service;

import com.touk.ticketbooker.TestData;
import com.touk.ticketbooker.exception.ClientNotFoundException;
import com.touk.ticketbooker.model.Client;
import com.touk.ticketbooker.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clientRepository);
    }

    @Test
    void getsertClientWithNameAndSurname() {
        Client newClient = new Client("Anakin", "Skywalker");
        when(clientRepository.findByNameAndSurname("Harry", "Weasley"))
                .thenReturn(Optional.of(TestData.client));
        when(clientRepository.findByNameAndSurname("Anakin", "Skywalker"))
                .thenReturn(Optional.of(newClient));

        assertThat(clientService.getClientByNameAndSurname("Harry", "Weasley"))
                .isNotNull()
                .isEqualTo(TestData.client);

        assertThat(clientService.getClientByNameAndSurname("Anakin", "Skywalker"))
                .isNotNull()
                .isSameAs(newClient);
    }

    @Test
    void getClientById() {
        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(TestData.client));
        when(clientRepository.findById(0L))
                .thenReturn(Optional.empty());

        assertThat(clientService.getClientById(1L))
                .isNotNull()
                .isEqualTo(TestData.client);

        assertThatThrownBy(() -> clientService.getClientById(0L))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("(Nic's Theatre Exception) Client with ID = 0 was not found!");
    }

    @Test
    void getClientByNameAndSurname() {
        when(clientRepository.findByNameAndSurname("Harry", "Weasley"))
                .thenReturn(Optional.of(TestData.client));
        when(clientRepository.findByNameAndSurname("Anakin", "Skywalker"))
                .thenReturn(Optional.empty());

        assertThat(clientService.getClientByNameAndSurname("Harry", "Weasley"))
                .isNotNull()
                .isEqualTo(TestData.client);

        assertThatThrownBy(() -> clientService.getClientByNameAndSurname("Anakin", "Skywalker"))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessage("(Nic's Theatre Exception) Client 'Anakin Skywalker' was not found!");
    }
}