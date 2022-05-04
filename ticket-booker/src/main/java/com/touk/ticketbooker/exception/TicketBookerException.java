package com.touk.ticketbooker.exception;


import com.touk.ticketbooker.Multiplex;

public class TicketBookerException extends RuntimeException {

    public TicketBookerException() {
    }

    public TicketBookerException(String message) {
        super(prependName(message));
    }

    public TicketBookerException(String message, Throwable cause) {
        super(prependName(message), cause);
    }

    private static String prependName(String message) {
        return "(" + Multiplex.MULTIPLEX_NAME + " Exception) " + message;
    }
}
