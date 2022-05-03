package com.touk.ticketbooker.exception;

public class ObjectNotFoundException extends TicketBookerException {
    private final Long objectId;

    public ObjectNotFoundException(Long id, String message) {
        super(message);
        objectId = id;
    }

    public ObjectNotFoundException(Long id, String message, Throwable cause) {
        super(message, cause);
        objectId = id;
    }
}
