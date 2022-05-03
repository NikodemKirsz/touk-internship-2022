package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.model.Room;
import com.touk.ticketbooker.model.Seat;
import com.touk.ticketbooker.model.dto.RoomInfoDto;
import com.touk.ticketbooker.model.dto.SeatDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoomToRoomInfoDtoConverter implements IConverter<Room, RoomInfoDto> {

    @Autowired
    private IConverter<Seat, SeatDto> seatToSeatDtoConverter;

    @Override
    public RoomInfoDto convert(Room room) {
        return RoomInfoDto.builder()
                .roomId(room.getId())
                .seats(room.getSeats().stream()
                        .map(seatToSeatDtoConverter::convert)
                        .collect(Collectors.toSet()))
                .build();
    }
}
