package com.touk.ticketbooker.controller.converter;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.exception.ConversionException;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static com.touk.ticketbooker.Multiplex.*;

@Component
public class StringToZonedDateTimeConverter implements IConverter<String, ZonedDateTime> {

    private final DateTimeFormatter formatter;

    public StringToZonedDateTimeConverter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public StringToZonedDateTimeConverter() {
        this(DEFAULT_DATE_FORMATTER);
    }


    @Override
    public ZonedDateTime convert(String timeString) {
        try {
            return ZonedDateTime.parse(timeString, formatter);
        }
        catch (DateTimeParseException dtpe) {
            throw new ConversionException("Cannot convert '" + timeString + "' to ZonedDateTime!", dtpe);
        }
    }
}
