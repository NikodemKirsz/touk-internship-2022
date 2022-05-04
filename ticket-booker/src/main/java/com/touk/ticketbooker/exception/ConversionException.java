package com.touk.ticketbooker.exception;

public class ConversionException extends TicketBookerException {
    public ConversionException(String message) {
        super(message);
    }

    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
