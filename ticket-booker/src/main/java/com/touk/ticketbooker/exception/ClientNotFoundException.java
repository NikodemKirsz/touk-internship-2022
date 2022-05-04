package com.touk.ticketbooker.exception;

public class ClientNotFoundException extends ObjectNotFoundException{

    public ClientNotFoundException(Long id, String message) {
        super(id, message);
    }

    public ClientNotFoundException(Long id, String message, Throwable cause) {
        super(id, message, cause);
    }
}
