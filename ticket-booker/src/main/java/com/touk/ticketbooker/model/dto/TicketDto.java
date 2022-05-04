package com.touk.ticketbooker.model.dto;

import com.touk.ticketbooker.model.TicketType;
import lombok.Builder;

@Builder
public record TicketDto (
        Long ticketId,
        TicketType ticketType,
        int seatRow,
        int seatNumber)
{ }
