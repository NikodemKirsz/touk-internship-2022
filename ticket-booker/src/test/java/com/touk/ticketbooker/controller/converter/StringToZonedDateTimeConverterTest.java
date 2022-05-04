package com.touk.ticketbooker.controller.converter;

import com.touk.ticketbooker.Multiplex;
import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.exception.ConversionException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StringToZonedDateTimeConverterTest {

    private final ZoneId zonedId = ZoneId.of("Europe/Warsaw");
    private final IConverter<String, ZonedDateTime> converter =
            new StringToZonedDateTimeConverter(DateTimeFormatter
                    .ofPattern("yyyy-MM-dd_HH:mm") // Example: 2022-04-30_20:21
                    .withZone(zonedId));

    @Test
    void convertStringToZonedDateTimeTest() {
        final String validStringDate1 = "2022-05-30_20:21";
        final ZonedDateTime validDate1 = ZonedDateTime.of(
                2022,
                05,
                30,
                20,
                21,
                00,
                00,
                zonedId);
        final String invalidStringDate1 = "2032-01-01-00:00";
        final String invalidStringDate2 = "2032-01-01T00:00";
        final String invalidStringDate3 = "2032-01-01_00:00:00";

        assertThat(converter.convert(validStringDate1))
                .isNotNull()
                .isEqualTo(validDate1);

        assertThatThrownBy(() -> converter.convert(invalidStringDate1))
                .isInstanceOf(ConversionException.class)
                .hasCauseInstanceOf(DateTimeParseException.class)
                .hasMessage("(Nic's Theatre Exception) Cannot convert '2032-01-01-00:00' to ZonedDateTime!");

        assertThatThrownBy(() -> converter.convert(invalidStringDate2))
                .isInstanceOf(ConversionException.class)
                .hasCauseInstanceOf(DateTimeParseException.class);

        assertThatThrownBy(() -> converter.convert(invalidStringDate3))
                .isInstanceOf(ConversionException.class)
                .hasCauseInstanceOf(DateTimeParseException.class);

    }
}