package com.touk.ticketbooker.exception;

public class NoSeatsChosenException extends TicketBookerException {

    public NoSeatsChosenException(String message) {
        super(message);
    }

    public NoSeatsChosenException(String message, Throwable cause) {
        super(message, cause);
    }
}
