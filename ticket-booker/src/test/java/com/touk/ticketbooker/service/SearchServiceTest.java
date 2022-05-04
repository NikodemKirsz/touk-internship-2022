package com.touk.ticketbooker.service;

import com.touk.ticketbooker.TestData;
import com.touk.ticketbooker.exception.ScreeningNotFoundException;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import com.touk.ticketbooker.repository.ScreeningRepository;
import com.touk.ticketbooker.converter.ScreeningToScreeningInfoDtoConverter;
import com.touk.ticketbooker.converter.SeatToSeatDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;

    private SearchService searchService;

    @BeforeEach
    void setUp() {
        searchService = new SearchService(
                screeningRepository,
                new ScreeningToScreeningInfoDtoConverter()
        );
    }

    @Test
    void getAllScreeningInfoForTime() {
        when(screeningRepository.findAll())
                .thenReturn(TestData.screenings);

        assertThat(searchService.getAllScreeningInfoForTime(TestData.staticDateTime.minusMinutes(60), 60))
                .isEmpty();

        assertThat(searchService.getAllScreeningInfoForTime(TestData.staticDateTime, 190))
                .isNotEmpty()
                .hasSize(1);

        assertThat(searchService.getAllScreeningInfoForTime(TestData.staticDateTime, 370))
                .isNotEmpty()
                .hasSize(3);
    }

    @Test
    void getScreeningInfoById() {
        when(screeningRepository.findById(1L))
                .thenReturn(Optional.of(TestData.screening));

        assertThat(searchService.getScreeningInfoById(1L))
                .isNotNull()
                .isInstanceOf(ScreeningInfoDto.class);
    }

    @Test
    void getScreeningById() {
        when(screeningRepository.findById(1L))
                .thenReturn(Optional.of(TestData.screening));
        when(screeningRepository.findById(0L))
                .thenReturn(Optional.empty());

        assertThat(searchService.getScreeningById(1L))
                .isNotNull()
                .isSameAs(TestData.screening);
        assertThatThrownBy(() -> searchService.getScreeningById(0L))
                .isInstanceOf(ScreeningNotFoundException.class)
                .hasMessage("(Nic's Theatre Exception) Screening with ID = 0 was not found!");
    }

    @Test
    void getAllScreeningInfos() {
        when(screeningRepository.findAll())
                .thenReturn(TestData.screenings);

        assertThat(searchService.getAllScreeningInfos())
                .isNotEmpty()
                .hasSize(4);
    }

    @Test
    void getAllScreenings() {
        when(screeningRepository.findAll())
                .thenReturn(TestData.screenings);

        assertThat(searchService.getAllScreenings())
                .isNotEmpty()
                .hasSize(4);
    }
}