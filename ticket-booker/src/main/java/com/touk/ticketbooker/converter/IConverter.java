package com.touk.ticketbooker.converter;

public interface IConverter<IN, OUT> {
    OUT convert(IN obj);
}
