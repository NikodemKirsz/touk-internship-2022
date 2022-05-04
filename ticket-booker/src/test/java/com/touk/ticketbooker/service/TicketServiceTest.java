package com.touk.ticketbooker.service;

import com.touk.ticketbooker.TestData;
import com.touk.ticketbooker.model.Ticket;
import com.touk.ticketbooker.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Captor
    private ArgumentCaptor<Set<Ticket>> ticketArgumentCaptor;

    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketService = new TicketService(ticketRepository);
    }

    @Test
    void saveTickets() {
        when(ticketRepository.saveAll(anySet()))
                .thenReturn(List.of(TestData.ticket));

        assertThat(ticketService.saveTickets(Set.of(TestData.ticket)))
                .isNotEmpty();

        verify(ticketRepository, times(1)).saveAll(ticketArgumentCaptor.capture());
        assertThat(ticketArgumentCaptor.getValue())
                .isNotEmpty()
                .hasSize(1);

    }
}