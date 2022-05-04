package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.exception.ConversionException;
import org.springframework.stereotype.Component;

@Component
public class StringToLongConverter implements IConverter<String, Long>{

    @Override
    public Long convert(String numberString) {
        try {
            return Long.valueOf(numberString);
        }
        catch (NumberFormatException nfe) {
            throw new ConversionException("Failed to convert '" + numberString + "' to a Long", nfe);
        }
    }
}
