package com.touk.ticketbooker.service;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.exception.ScreeningNotFoundException;
import com.touk.ticketbooker.model.Room;
import com.touk.ticketbooker.model.Screening;
import com.touk.ticketbooker.model.Seat;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import com.touk.ticketbooker.model.dto.SeatDto;
import com.touk.ticketbooker.repository.ScreeningRepository;
import com.touk.ticketbooker.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ScreeningRepository screeningRepository;
    private final IConverter<Screening, ScreeningInfoDto> screeningToScreeningInfoDtoConverter;

    public Set<ScreeningInfoDto> getAllScreeningInfoForTime(ZonedDateTime start, int length) {
        ZonedDateTime end = start.plusMinutes(length);
        return getAllScreeningInfos().stream()
                .filter(screening -> Utils.isScreeningBetween(screening, start, end))
                .collect(Collectors.toSet());
    }

    public ScreeningInfoDto getScreeningInfoById(Long screeningId) {
        return screeningToScreeningInfoDtoConverter.convert(getScreeningById(screeningId));
    }

    public Screening getScreeningById(Long screeningId) {
        return screeningRepository.findById(screeningId).orElseThrow(
                () -> new ScreeningNotFoundException(screeningId, "Screening with ID = " + screeningId + " was not found!")
        );
    }

    public Set<ScreeningInfoDto> getAllScreeningInfos() {
        return getAllScreenings().stream()
                .map(screeningToScreeningInfoDtoConverter::convert)
                .collect(Collectors.toSet());
    }

    public Set<Screening> getAllScreenings() {
        return Set.copyOf(screeningRepository.findAll());
    }
}
