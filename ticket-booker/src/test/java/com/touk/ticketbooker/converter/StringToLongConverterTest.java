package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.exception.ConversionException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StringToLongConverterTest {

    private final IConverter<String, Long> converter = new StringToLongConverter();

    @Test
    void stringToLongConversion() {
        String validLongString1 = "41";
        String validLongString2 = "-41";
        String invalidLongString1 = "0.0";
        String invalidLongString2 = "twenty-one";

        assertThat(converter.convert(validLongString1))
                .isEqualTo(41L);
        assertThat(converter.convert(validLongString2))
                .isEqualTo(-41L);

        assertThatThrownBy(() -> converter.convert(invalidLongString1))
                .isInstanceOf(ConversionException.class)
                .hasCauseInstanceOf(NumberFormatException.class)
                .hasMessage("(Nic's Theatre Exception) Failed to convert '0.0' to a Long");
        assertThatThrownBy(() -> converter.convert(invalidLongString2))
                .isInstanceOf(ConversionException.class)
                .hasCauseInstanceOf(NumberFormatException.class)
                .hasMessage("(Nic's Theatre Exception) Failed to convert 'twenty-one' to a Long");
    }
}