package com.touk.ticketbooker.exception;

public class TooLateForReservationException extends TicketBookerException {

    public TooLateForReservationException(String message) {
        super(message);
    }

    public TooLateForReservationException(String message, Throwable cause) {
        super(message, cause);
    }
}
