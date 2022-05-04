package com.touk.ticketbooker.controller;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.model.dto.SeatDto;
import com.touk.ticketbooker.model.dto.TicketDto;
import com.touk.ticketbooker.util.Utils;
import com.touk.ticketbooker.validator.IValidator;
import com.touk.ticketbooker.exception.*;
import com.touk.ticketbooker.model.*;
import com.touk.ticketbooker.model.dto.ReceiptDto;
import com.touk.ticketbooker.service.ClientService;
import com.touk.ticketbooker.service.SearchService;
import com.touk.ticketbooker.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.touk.ticketbooker.Multiplex.*;
import static org.springframework.http.HttpStatus.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ClientService clientService;
    private final TicketService ticketService;
    private final SearchService searchService;
    private final IConverter<String, Long> stringToLongConverter;
    private final IConverter<String, TicketType> stringToTicketTypeConverter;
    private final IConverter<Ticket, TicketDto> ticketToTicketDtoConverter;
    private final IValidator<String> nameValidator;
    private final IValidator<String> surnameValidator;

    @PostMapping("/{screeningId}")
    public ResponseEntity<ReceiptDto> makeReservation(
            @PathVariable Long screeningId,
            @RequestParam @NotBlank String name,
            @RequestParam @NotBlank String surname,
            @RequestBody Map<String, String> seatsToTypes) {

        if (nameValidator.isInvalid(name)) {
            throw new ValidationException("Name '" + name + "' is not in valid format!");
        }
        if (surnameValidator.isInvalid(surname)) {
            throw new ValidationException("Surname '" + surname + "' is not in valid format!");
        }
        if (seatsToTypes.size() < 1) {
            throw new NoSeatsChosenException("You must reserve at least one seat!");
        }

        Screening screening = searchService.getScreeningById(screeningId);
        if (Utils.isReservationTooLate(screening)) {
            throw new TooLateForReservationException(
                    "You can not reserve ticket " + MAX_RESERVATION_TIME_BEFORE_SCREENING + " minutes before the screening!"
            );
        }

        Client client = clientService.getsertClientWithNameAndSurname(name, surname);
        Set<Ticket> tickets = createTicketsFromSeatsToTypesMap(seatsToTypes, screening, client);

        Set<Ticket> savedTickets = ticketService.saveTickets(tickets);
        return ResponseEntity.status(CREATED)
                .body(new ReceiptDto(
                        screening,
                        savedTickets.stream()
                                .map(ticketToTicketDtoConverter::convert)
                                .collect(Collectors.toSet())
                        )
                );
    }

    private Set<Ticket> createTicketsFromSeatsToTypesMap(Map<String, String> seatsToTypes, Screening screening, Client client){
        Set<Ticket> tickets = new HashSet<>(seatsToTypes.size());
        Set<Long> chosenSeatsIds = new HashSet<>(seatsToTypes.size());

        Set<Long> availableSeatsIds = Utils.getFreeSeatDtosForScreening(screening).stream()
                .map(SeatDto::seatId)
                .collect(Collectors.toSet());
        Set<Seat> roomSeats = screening.getRoom().getSeats();

        seatsToTypes.forEach((seatIdString, ticketTypeString) -> {
            Long seatId = stringToLongConverter.convert(seatIdString);
            chosenSeatsIds.add(seatId);
            if (!availableSeatsIds.contains(seatId)) {
                throw new SeatUnavailableException("Seat with ID = " + seatId + " is already taken!");
            }

            Seat seat = roomSeats.stream()
                    .filter(s -> seatId.equals(s.getId()))
                    .findFirst().orElseThrow(
                            () -> new SeatNotFoundException(seatId, "Seat with ID = " + seatId + " was not found!")
                    );
            TicketType type = stringToTicketTypeConverter.convert(ticketTypeString);
            Ticket ticket = Ticket.builder()
                    .type(type)
                    .client(client)
                    .screening(screening)
                    .seat(seat)
                    .build();
            tickets.add(ticket);
        });

        if (!Utils.areRequestedSeatsValid(screening, chosenSeatsIds)) {
            throw new LonelySeatException("There is a single seat left between taken ones!");
        }

        return tickets;
    }
}
