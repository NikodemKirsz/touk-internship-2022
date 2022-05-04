package com.touk.ticketbooker.controller;

import com.touk.ticketbooker.TestData;
import com.touk.ticketbooker.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {

    @MockBean
    private SearchService searchService;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        when(searchService.getAllScreeningInfoForTime(Mockito.any(ZonedDateTime.class), anyInt()))
                .thenReturn(Set.copyOf(TestData.screeningInfos));
        when(searchService.getScreeningInfoById(anyLong()))
                .thenReturn(TestData.screeningInfos.get(0));
        when(searchService.getScreeningById(anyLong()))
                .thenReturn(TestData.screenings.get(0));
    }

    @Test
    void getAllScreeningInfosForTime() throws Exception {
        mockMvc.perform(get("/search/screenings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(4)))
                .andExpect(jsonPath("$[0].screeningId", is(1)))
                .andExpect(jsonPath("$[0].movieTitle", is("Movie 1")));
    }

    @Test
    void getScreeningInfoById() throws Exception {
        mockMvc.perform(get("/search/screenings/{id}", anyLong()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.screening").hasJsonPath())
                .andExpect(jsonPath("$.screening..screeningId").hasJsonPath())
                .andExpect(jsonPath("$.screening..movieTitle").hasJsonPath())
                .andExpect(jsonPath("$.screening..start").hasJsonPath())
                .andExpect(jsonPath("$.screening..end").hasJsonPath())
                .andExpect(jsonPath("$.available_seats..seatId").hasJsonPath())
                .andExpect(jsonPath("$.available_seats..row").hasJsonPath())
                .andExpect(jsonPath("$.available_seats..number").hasJsonPath());
    }
}