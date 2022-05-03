package com.touk.ticketbooker.exception;

public class SeatUnavailableException extends TicketBookerException {

    public SeatUnavailableException(String message) {
        super(message);
    }

    public SeatUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
