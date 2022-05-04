package com.touk.ticketbooker.exception;

public class LonelySeatException extends TicketBookerException {

    public LonelySeatException(String message) {
        super(message);
    }

    public LonelySeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
