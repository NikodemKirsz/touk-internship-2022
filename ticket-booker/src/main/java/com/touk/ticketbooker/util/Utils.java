package com.touk.ticketbooker.util;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.converter.SeatToSeatDtoConverter;
import com.touk.ticketbooker.model.Screening;
import com.touk.ticketbooker.model.Seat;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import com.touk.ticketbooker.model.dto.SeatDto;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    private static final IConverter<Seat, SeatDto> SeatToSeatDtoConverter = new SeatToSeatDtoConverter();

    private Utils() {}

    public static boolean isScreeningBetween(ScreeningInfoDto screening, ZonedDateTime from, ZonedDateTime to) {
        return screening.start().isAfter(from.minusMinutes(1))
                && screening.end().isBefore(to.plusMinutes(1));
    }

    public static Set<SeatDto> getFreeSeatDtosForScreening(Screening screening) {
        Long screeningId = screening.getId();
        Set<Seat> allSeats = screening.getRoom().getSeats();
        Set<SeatDto> freeSeats = new HashSet<>(allSeats.size());

        for (var seat : allSeats) {
            if (isSeatFree(seat, screeningId)) {
                freeSeats.add(SeatToSeatDtoConverter.convert(seat));
            }
        }

        return freeSeats;
    }

    public static boolean isSeatFree(Seat seat, Long screeningId) {
        for (var ticket : seat.getTickets()) {
            if (screeningId.equals(ticket.getScreening().getId())) return false;
        }
        return true;
    }
}
