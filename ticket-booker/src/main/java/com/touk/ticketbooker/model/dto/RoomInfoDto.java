package com.touk.ticketbooker.model.dto;

import com.touk.ticketbooker.model.Seat;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.Set;

@Builder
public record RoomInfoDto(
        Long roomId,
        Set<SeatDto> seats)
{ }
