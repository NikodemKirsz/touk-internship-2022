package com.touk.ticketbooker.exception;

public class ValidationException extends TicketBookerException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
