package com.touk.ticketbooker.exception;

public class SeatNotFoundException extends ObjectNotFoundException {

    public SeatNotFoundException(Long id, String message) {
        super(id, message);
    }

    public SeatNotFoundException(Long id, String message, Throwable cause) {
        super(id, message, cause);
    }
}
