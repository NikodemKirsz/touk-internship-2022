package com.touk.ticketbooker.util;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.converter.SeatToSeatDtoConverter;
import com.touk.ticketbooker.exception.TicketBookerException;
import com.touk.ticketbooker.model.Screening;
import com.touk.ticketbooker.model.Seat;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import com.touk.ticketbooker.model.dto.SeatDto;
import org.springframework.data.util.Pair;

import java.time.ZonedDateTime;
import java.util.*;

import static com.touk.ticketbooker.Multiplex.*;
import static com.touk.ticketbooker.Multiplex.MAX_RESERVATION_TIME_BEFORE_SCREENING;

public class Utils {

    private static final IConverter<Seat, SeatDto> SeatToSeatDtoConverter = new SeatToSeatDtoConverter();

    private Utils() {}

    public static boolean areRequestedSeatsValid(Screening screening, Set<Long> chosenSeatsIds) {
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

        final int overlap = 3;
        Pair<Long, Long> minMaxSeatId = getSetMinMax(chosenSeatsIds);
        assert minMaxSeatId != null;
        int minSeat = minMaxSeatId.getFirst().intValue();
        int maxSeatId = minMaxSeatId.getSecond().intValue();

        int freeConsecutive = 0;
        for (int i = minSeat >= overlap ? minSeat - overlap : 0; i < (maxSeatId <= capacity - overlap ? maxSeatId + overlap : capacity); i++) {
            boolean isFirst = i % ROOM_SEATS_PER_ROW == 0;
            boolean willBeTaken = !Utils.isSeatFree(allSeatsDaa[i], screening.getId())
                    || chosenSeatsIds.contains((long)(i + 1));

            if (willBeTaken || isFirst) {
                if (freeConsecutive == 1) return false;
                freeConsecutive = 0;
            }
            if (!willBeTaken) freeConsecutive++;
        }

        return true;
    }

    public static boolean isReservationTooLate(Screening screening) {
        return screening.getTime().isBefore(
                ZonedDateTime.now(DEFAULT_ZONE_ID).plusMinutes(MAX_RESERVATION_TIME_BEFORE_SCREENING)
        );
    }

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

    public static <T extends Comparable<T>> Pair<T, T> getSetMinMax(Set<T> set) {
        if(set.isEmpty()) return null;

        Iterator<T> iterator = set.iterator();
        T first = iterator.next();
        T min = first;
        T max = first;

        while (iterator.hasNext()) {
            T number = iterator.next();
            if (number.compareTo(min) < 0) min = number;
            else if (number.compareTo(max) > 0) max = number;
        }

        return Pair.of(min, max);
    }
}
