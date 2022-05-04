package com.touk.ticketbooker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.touk.ticketbooker.Multiplex.*;

@Getter
@RequiredArgsConstructor
public enum TicketType {
    ADULT(TICKET_PRICE_ADULT),
    STUDENT(TICKET_PRICE_STUDENT),
    CHILD(TICKET_PRICE_CHILD);

    private final double price;
}
