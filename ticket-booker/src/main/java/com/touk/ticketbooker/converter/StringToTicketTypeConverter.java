package com.touk.ticketbooker.converter;

import com.touk.ticketbooker.converter.IConverter;
import com.touk.ticketbooker.exception.ConversionException;
import com.touk.ticketbooker.model.TicketType;
import org.springframework.stereotype.Component;

@Component
public class StringToTicketTypeConverter implements IConverter<String, TicketType> {

    @Override
    public TicketType convert(String typeString) {
        try {
            return TicketType.valueOf(typeString);
        }
        catch(IllegalArgumentException iae) {
            throw new ConversionException("Failed to convert '" + typeString + "' to a TicketType", iae);
        }
    }
}
