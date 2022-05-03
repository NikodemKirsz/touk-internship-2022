package com.touk.ticketbooker;

import com.touk.ticketbooker.model.*;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import com.touk.ticketbooker.model.dto.SeatDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.touk.ticketbooker.Multiplex.*;

public class TestData {
    private TestData() { }

    public static final ZonedDateTime staticDateTime = ZonedDateTime.of(
            2024,
            06,
            06,
            16,
            00,
            00,
            00,
            DEFAULT_ZONE_ID
    );
    public static final List<ScreeningInfoDto> screeningInfos = List.of(
            ScreeningInfoDto.builder()
                    .screeningId(1L)
                    .movieTitle("Movie 1")
                    .start(staticDateTime)
                    .end(staticDateTime.plusHours(2))
                    .build(),
            ScreeningInfoDto.builder()
                    .screeningId(2L)
                    .movieTitle("Movie 2")
                    .start(staticDateTime.plusHours(2))
                    .end(staticDateTime.plusHours(4))
                    .build(),
            ScreeningInfoDto.builder()
                    .screeningId(3L)
                    .movieTitle("Movie 3")
                    .start(staticDateTime.plusHours(4))
                    .end(staticDateTime.plusHours(6))
                    .build(),
            ScreeningInfoDto.builder()
                    .screeningId(4L)
                    .movieTitle("Movie 4")
                    .start(staticDateTime.plusHours(6))
                    .end(staticDateTime.plusHours(8))
                    .build()
            );
    public static final List<Seat> seats = new ArrayList<>(ROOM_SEATS_CAPACITY);
    public static final List<SeatDto> availableSeatDtos = new ArrayList<>(ROOM_SEATS_CAPACITY);
    public static final List<SeatDto> seatDtos = new ArrayList<>(ROOM_SEATS_CAPACITY);
    public static final Movie movie;
    public static final Room room;
    public static final Screening screening;
    public static List<Screening> screenings;
    public static final Client client;
    public static final Ticket ticket;

    static {
        for (int i = 1; i <= ROOM_SEATS_CAPACITY; i++) {
            int row = i / ROOM_SEATS_PER_ROW;
            int number = i % ROOM_SEATS_PER_ROW;

            Seat seat = new Seat((long)i, row, number);
            SeatDto seatDto = SeatDto.builder().seatId((long)i).row(row).number(number).build();

            seats.add(seat);
            seatDtos.add(seatDto);
            if (i < ROOM_SEATS_PER_ROW * 3) availableSeatDtos.add(seatDto);
        }

        movie = new Movie(
                "Lord of the Caribbean",
                120
        );

        room = new Room(
                1L,
                Set.copyOf(seats)
        );

        screening = new Screening(
                1L,
                staticDateTime,
                movie,
                room,
                null
        );

        client = new Client(
                "Harry",
                "Weasley"
        );

        ticket = new Ticket(
                TicketType.ADULT,
                client,
                screening,
                seats.get(0)
        );

        screenings = List.of(
                screening,
                new Screening(
                        2L,
                        staticDateTime.plusHours(2),
                        movie,
                        room,
                        null),
                new Screening(
                        3L,
                        staticDateTime.plusHours(4),
                        movie,
                        room,
                        null),
                new Screening(
                        4L,
                        staticDateTime.plusHours(6),
                        movie,
                        room,
                        null)
        );

        Set<Ticket> tickets = Set.of(ticket);
        for (int i = ROOM_SEATS_PER_ROW * 3; i < seats.size(); i++) {
            seats.get(i).setTickets(tickets);
        }
    }
}
