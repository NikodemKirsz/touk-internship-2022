package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.model.Seat;
import com.touk.ticketbooker.model.Ticket;
import com.touk.ticketbooker.model.dto.TicketDto;
import org.springframework.stereotype.Component;

@Component
public class TicketToTicketDtoConverter implements IConverter<Ticket, TicketDto> {

    @Override
    public TicketDto convert(Ticket ticket) {
        Seat seat = ticket.getSeat();
        return TicketDto.builder()
                .ticketId(ticket.getId())
                .ticketType(ticket.getType())
                .seatRow(seat.getRow())
                .seatNumber(seat.getNumber())
                .build();
    }
}
