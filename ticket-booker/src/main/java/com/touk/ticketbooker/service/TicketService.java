package com.touk.ticketbooker.service;

import com.touk.ticketbooker.model.Ticket;
import com.touk.ticketbooker.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Set<Ticket> saveTickets(Set<Ticket> tickets) {
        List<Ticket> savedTickets = ticketRepository.saveAll(tickets);
        return Set.copyOf(savedTickets);
    }
}
