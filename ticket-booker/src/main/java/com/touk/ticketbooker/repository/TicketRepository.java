package com.touk.ticketbooker.repository;

import com.touk.ticketbooker.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {}
