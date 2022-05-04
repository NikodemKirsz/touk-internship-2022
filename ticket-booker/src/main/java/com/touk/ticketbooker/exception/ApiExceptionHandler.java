package com.touk.ticketbooker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { LonelySeatException.class })
    public ResponseEntity<ApiException> handleLonelySeatException(LonelySeatException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { SeatUnavailableException.class })
    public ResponseEntity<ApiException> handleSeatUnavailableException(SeatUnavailableException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { ConversionException.class })
    public ResponseEntity<ApiException> handleConversionException(ConversionException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { ValidationException.class })
    public ResponseEntity<ApiException> handleValidationException(ValidationException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { TooLateForReservationException.class })
    public ResponseEntity<ApiException> handleTooLateForReservationException(TooLateForReservationException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { NoSeatsChosenException.class })
    public ResponseEntity<ApiException> handleNoSeatsChosenException(NoSeatsChosenException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { ScreeningNotFoundException.class })
    public ResponseEntity<ApiException> handleScreeningNotFoundException(ScreeningNotFoundException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { ClientNotFoundException.class })
    public ResponseEntity<ApiException> handleClientNotFoundException(ClientNotFoundException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { RoomNotFoundException.class })
    public ResponseEntity<ApiException> handleRoomNotFoundException(RoomNotFoundException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { SeatNotFoundException.class })
    public ResponseEntity<ApiException> handleSeatNotFoundException(SeatNotFoundException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { ObjectNotFoundException.class })
    public ResponseEntity<ApiException> handleObjectNotFoundException(ObjectNotFoundException e) {
        HttpStatus resultStatus = BAD_REQUEST;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    @ExceptionHandler(value = { TicketBookerException.class })
    public ResponseEntity<ApiException> handleTicketBookerException(TicketBookerException e) {
        HttpStatus resultStatus = INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(resultStatus)
                .body(createApiException(e, resultStatus));
    }

    private static <T extends TicketBookerException> ApiException createApiException(T e, HttpStatus resultStatus) {
        return new ApiException(
                e.getMessage(),
                e,
                resultStatus,
                ZonedDateTime.now(ZoneOffset.UTC)
        );
    }
}
