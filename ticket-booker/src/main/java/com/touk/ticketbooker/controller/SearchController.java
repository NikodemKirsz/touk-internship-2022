package com.touk.ticketbooker.controller;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.model.Screening;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import com.touk.ticketbooker.model.dto.SeatDto;
import com.touk.ticketbooker.service.SearchService;
import com.touk.ticketbooker.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.touk.ticketbooker.Multiplex.*;
import static org.springframework.http.HttpStatus.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;
    private final IConverter<String, ZonedDateTime> stringToZonedDateTimeConverter;

    @GetMapping("/screenings")
    public ResponseEntity<List<ScreeningInfoDto>> getAllScreeningInfosForTime(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) Integer length) {

        Set<ScreeningInfoDto> screenings;

        if (start == null || start.isBlank()) {
            screenings = searchService.getAllScreeningInfoForTime(
                    ZonedDateTime.now(DEFAULT_ZONE_ID),
                    DEFAULT_SEARCH_LENGTH
            );
        }
        else {
            screenings = searchService.getAllScreeningInfoForTime(
                    stringToZonedDateTimeConverter.convert(start),
                    (length == null || length < 1) ? DEFAULT_SEARCH_LENGTH : length
            );
        }

        return ResponseEntity.status(OK)
                .body(sortByTitleAndStartTime(screenings));
    }

    @GetMapping("/screenings/{id}")
    public ResponseEntity<Map<String, Object>> getScreeningInfoById(
            @PathVariable Long id) {
        Screening screening = searchService.getScreeningById(id);

        return ResponseEntity.status(OK)
                .body(Map.of(
                        "screening", searchService.getScreeningInfoById(id),
                        "available_seats", Utils.getFreeSeatDtosForScreening(screening).stream()
                                .sorted(Comparator.comparing(SeatDto::seatId)).toList()
                        )
                );
    }

    private List<ScreeningInfoDto> sortByTitleAndStartTime(Set<ScreeningInfoDto> screeningInfos) {
        return screeningInfos.stream()
                .sorted(Comparator
                        .comparing(ScreeningInfoDto::movieTitle)
                        .thenComparing(ScreeningInfoDto::start))
                .toList();
    }
}
