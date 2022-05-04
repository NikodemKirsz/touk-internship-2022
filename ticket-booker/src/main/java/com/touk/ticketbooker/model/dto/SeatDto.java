package com.touk.ticketbooker.model.dto;

import com.touk.ticketbooker.model.Room;
import lombok.Builder;

@Builder
public record SeatDto(
        Long seatId,
        int row,
        int number)
{ }
