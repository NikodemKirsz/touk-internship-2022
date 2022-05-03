package com.touk.ticketbooker.exception;

public class ScreeningNotFoundException extends ObjectNotFoundException {

    public ScreeningNotFoundException(Long id, String message) {
        super(id, message);
    }

    public ScreeningNotFoundException(Long id, String message, Throwable cause) {
        super(id, message, cause);
    }
}
