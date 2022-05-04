package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.exception.ConversionException;
import com.touk.ticketbooker.model.TicketType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringToTicketTypeConverterTest {

    private final IConverter<String, TicketType> converter = new StringToTicketTypeConverter();

    @Test
    void stringToTicketTypeConversion() {
        String validTypeString1 = "ADULT";
        String validTypeString2 = "STUDENT";
        String invalidTypeString1 = "child";
        String invalidTypeString2 = "pierogi";

        assertThat(converter.convert(validTypeString1))
                .isEqualTo(TicketType.ADULT);
        assertThat(converter.convert(validTypeString2))
                .isEqualTo(TicketType.STUDENT);

        assertThatThrownBy(() -> converter.convert(invalidTypeString1))
                .isInstanceOf(ConversionException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessage("(Nic's Theatre Exception) Failed to convert 'child' to a TicketType");
        assertThatThrownBy(() -> converter.convert(invalidTypeString2))
                .isInstanceOf(ConversionException.class)
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessage("(Nic's Theatre Exception) Failed to convert 'pierogi' to a TicketType");
    }
}