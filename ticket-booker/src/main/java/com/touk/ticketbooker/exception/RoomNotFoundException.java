package com.touk.ticketbooker.exception;

public class RoomNotFoundException extends ObjectNotFoundException {

    public RoomNotFoundException(Long id, String message) {
        super(id, message);
    }

    public RoomNotFoundException(Long id, String message, Throwable cause) {
        super(id, message, cause);
    }
}
