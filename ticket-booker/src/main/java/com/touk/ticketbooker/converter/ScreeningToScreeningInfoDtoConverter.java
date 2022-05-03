package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.model.Movie;
import com.touk.ticketbooker.model.Screening;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class ScreeningToScreeningInfoDtoConverter implements IConverter<Screening, ScreeningInfoDto> {

    @Override
    public ScreeningInfoDto convert(Screening screening) {
        Movie movie = screening.getMovie();

        long screeningId = screening.getId();
        String movieTitle = movie.getTitle();
        ZonedDateTime start = screening.getTime();
        ZonedDateTime end = ZonedDateTime.from(start.plusMinutes(movie.getDuration()));

        return ScreeningInfoDto.builder()
                .screeningId(screeningId)
                .movieTitle(movieTitle)
                .start(start)
                .end(end)
                .build();
    }
}
