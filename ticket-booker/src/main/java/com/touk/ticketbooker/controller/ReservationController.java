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
        if (isReservationTooLate(screening)) {
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

    private static boolean isReservationTooLate(Screening screening) {
        return screening.getTime().isBefore(
                ZonedDateTime.now(DEFAULT_ZONE_ID).plusMinutes(MAX_RESERVATION_TIME_BEFORE_SCREENING)
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

        if (!areRequestedSeatsValid(screening, chosenSeatsIds)) {
            throw new LonelySeatException("There is a single seat left between taken ones!");
        }

        return tickets;
    }

    private boolean areRequestedSeatsValid(Screening screening, Set<Long> chosenSeatsIds) {
        final int capacity = ROOM_SEATS_CAPACITY;

        Set<Seat> allSeats = screening.getRoom().getSeats();
        if (allSeats.size() != capacity) {
            throw new TicketBookerException("INTERNAL ERROR! Invalid number of seats per room (" + allSeats.size() + ")!");
        }

        Seat[] allSeatsDaa = new Seat[capacity];
        for (var seat : allSeats) {
            int index = (seat.getId().intValue() - 1) % capacity;
           allSeatsDaa[index] = seat;
        }

        int freeConsecutive = 0;
        for (int i = 0; i < capacity; i++) {
            boolean isFirst = i % ROOM_SEATS_PER_ROW == 0;
            boolean willBeFree = Utils.isSeatFree(allSeatsDaa[i], screening.getId())
                            && !chosenSeatsIds.contains((long)(i + 1));

            if (isFirst) {
                if (freeConsecutive == 1) return false;
                freeConsecutive = 0;
            }

            if (!willBeFree) {
                if (freeConsecutive == 1) return false;
                freeConsecutive = 0;
            }
            else {
               freeConsecutive++;
            }
        }

        return true;
    }

    private static void printSeating(Screening screening) {
        final int capacity = ROOM_SEATS_CAPACITY;
        Set<Seat> allSeats = screening.getRoom().getSeats();

        Seat[] allSeatsDaa = new Seat[capacity];
        for (var seat : allSeats) {
            int index = (seat.getId().intValue() - 1) % capacity;
            allSeatsDaa[index] = seat;
        }

        for (int i = 0; i < capacity; i++) {
            char character;
            if (i % ROOM_SEATS_PER_ROW == 0) System.out.println();

            if (Utils.isSeatFree(allSeatsDaa[i], screening.getId())) {
                character = '-';
            }
            else {
                character = '+';
            }
            System.out.print(character);
        }
        System.out.println();
    }
}
