package com.touk.ticketbooker.controller;

import com.touk.ticketbooker.TestData;
import com.touk.ticketbooker.service.ClientService;
import com.touk.ticketbooker.service.SearchService;
import com.touk.ticketbooker.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @MockBean
    private ClientService clientService;
    @MockBean
    private TicketService ticketService;
    @MockBean
    private SearchService searchService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        when(searchService.getScreeningById(anyLong()))
                .thenReturn(TestData.screening);

        when(clientService.getsertClientWithNameAndSurname(anyString(), anyString()))
                .thenReturn(TestData.client);
        when(clientService.getClientById(anyLong()))
                .thenReturn(TestData.client);

        when(ticketService.saveTickets(anySet()))
                .thenReturn(Set.of(TestData.ticket));
    }

    @Test
    void makeReservationPassedTest() throws Exception {
        String acceptableJson =
        """
        {
            "6": "ADULT",
            "9": "CHILD",
            "10": "CHILD"
        }
        """;
        mockMvc.perform(post("/reservation/{screeningId}", 1)
                        .param("name", "Bilbo")
                        .param("surname", "Baggins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(acceptableJson)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.movieTitle").hasJsonPath())
                .andExpect(jsonPath("$.startTime").hasJsonPath())
                .andExpect(jsonPath("$.expirationTime").hasJsonPath())
                .andExpect(jsonPath("$.total").hasJsonPath())
                .andExpect(jsonPath("$.tickets").hasJsonPath());
    }

    @Test
    void makeReservationFailedCauseOfRequestBodyTest() throws Exception {
        String emptyJson = "{}";
        mockMvc.perform(post("/reservation/{screeningId}", 1)
                        .param("name", "Bilbo")
                        .param("surname", "Baggins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emptyJson)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("(Nic's Theatre Exception) You must reserve at least one seat!"))
                .andExpect(jsonPath("$.timestamp").hasJsonPath())
                .andExpect(jsonPath("$.httpStatus").hasJsonPath())
                .andExpect(jsonPath("$.throwable").hasJsonPath());
    }

    @Test
    void makeReservationFailedCauseOfTakenSeatsTest() throws Exception {
        String acceptableJson =
                """
                {
                    "81": "ADULT",
                    "82": "CHILD"
                }
                """;
        mockMvc.perform(post("/reservation/{screeningId}", 1)
                        .param("name", "Bilbo")
                        .param("surname", "Baggins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(acceptableJson)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("(Nic's Theatre Exception) Seat with ID = 81 is already taken!"));
    }

    @Test
    void makeReservationFailedCauseOfLonelyMiddleSeatTest() throws Exception {
        String acceptableJson =
                """
                {
                    "21": "ADULT",
                    "23": "CHILD"
                }
                """;
        mockMvc.perform(post("/reservation/{screeningId}", 1)
                        .param("name", "Bilbo")
                        .param("surname", "Baggins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(acceptableJson)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("(Nic's Theatre Exception) There is a single seat left between taken ones!"));
    }

    @Test
    void makeReservationFailedCauseOfLonelyFirstSeatTest() throws Exception {
        String acceptableJson =
                """
                {
                    "22": "ADULT",
                    "23": "CHILD"
                }
                """;
        mockMvc.perform(post("/reservation/{screeningId}", 1)
                        .param("name", "Bilbo")
                        .param("surname", "Baggins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(acceptableJson)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("(Nic's Theatre Exception) There is a single seat left between taken ones!"));
    }

    @Test
    void makeReservationFailedCauseOfLonelyLastSeatTest() throws Exception {
        String acceptableJson =
                """
                {
                    "37": "ADULT",
                    "38": "ADULT",
                    "39": "ADULT"
                }
                """;
        mockMvc.perform(post("/reservation/{screeningId}", 1)
                        .param("name", "Bilbo")
                        .param("surname", "Baggins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(acceptableJson)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("(Nic's Theatre Exception) There is a single seat left between taken ones!"));
    }
}