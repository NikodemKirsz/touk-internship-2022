package com.touk.ticketbooker.model.dto;

import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record ScreeningInfoDto(
    Long screeningId,
    String movieTitle,
    ZonedDateTime start,
    ZonedDateTime end)
{ }
