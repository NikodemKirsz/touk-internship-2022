package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.model.Seat;
import com.touk.ticketbooker.model.dto.SeatDto;
import org.springframework.stereotype.Component;

@Component
public class SeatToSeatDtoConverter implements IConverter<Seat, SeatDto> {

    @Override
    public SeatDto convert(Seat seat) {
        return SeatDto.builder()
                .seatId(seat.getId())
                .row(seat.getRow())
                .number(seat.getNumber())
                .build();
    }
}
