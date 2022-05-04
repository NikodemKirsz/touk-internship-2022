package com.touk.ticketbooker.util;

import com.touk.ticketbooker.TestData;
import com.touk.ticketbooker.model.Screening;
import com.touk.ticketbooker.model.Seat;
import com.touk.ticketbooker.model.dto.ScreeningInfoDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void isScreeningBetween() {
        ScreeningInfoDto screening = TestData.screeningInfos.get(0);

        ZonedDateTime validStart = TestData.staticDateTime.minusMinutes(29);
        ZonedDateTime validEnd = TestData.staticDateTime.plusHours(2);

        ZonedDateTime invalidStart1 = TestData.staticDateTime.plusMinutes(29);
        ZonedDateTime invalidEnd1 = TestData.staticDateTime.plusHours(2);

        ZonedDateTime invalidStart2 = TestData.staticDateTime.minusMinutes(29);
        ZonedDateTime invalidEnd2 = TestData.staticDateTime.plusHours(1);

        assertTrue(Utils.isScreeningBetween(screening, validStart, validEnd));
        assertFalse(Utils.isScreeningBetween(screening, invalidStart1, invalidEnd1));
        assertFalse(Utils.isScreeningBetween(screening, invalidStart2, invalidEnd2));
    }

    @Test
    void isSeatFree() {
        Long screeningId = TestData.screening.getId();
        Seat freeSeat1 = TestData.seats.get(0);
        Seat freeSeat2 = TestData.seats.get(12);
        Seat takenSeat1 = TestData.seats.get(120);
        Seat takenSeat2 = TestData.seats.get(183);

        assertTrue(Utils.isSeatFree(freeSeat1, screeningId));
        assertTrue(Utils.isSeatFree(freeSeat2, screeningId));
        assertFalse(Utils.isSeatFree(takenSeat1, screeningId));
        assertFalse(Utils.isSeatFree(takenSeat2, screeningId));
    }

    @Test
    void getSetMinMax() {
        Pair<Integer, Integer> results1 = Utils.getSetMinMax(Set.of(1, 2, 3, 4));
        Pair<Integer, Integer> results2 = Utils.getSetMinMax(Set.of(5, 1, 8, 19, 120, 3));
        Pair<Integer, Integer> results3 = Utils.getSetMinMax(Set.of(5));
        Pair<Integer, Integer> results4 = Utils.getSetMinMax(new HashSet<Integer>());

        assertThat(results1)
                .isNotNull()
                .isInstanceOf(Pair.class);
        assertThat(results1.getFirst())
                .isEqualTo(1);
        assertThat(results1.getSecond())
                .isEqualTo(4);

        assertThat(results2)
                .isNotNull()
                .isInstanceOf(Pair.class);
        assertThat(results2.getFirst())
                .isEqualTo(1);
        assertThat(results2.getSecond())
                .isEqualTo(120);

        assertThat(results3)
                .isNotNull()
                .isInstanceOf(Pair.class);
        assertThat(results3.getFirst())
                .isEqualTo(5);
        assertThat(results3.getSecond())
                .isEqualTo(5);

        assertThat(results4)
                .isNull();
    }
}