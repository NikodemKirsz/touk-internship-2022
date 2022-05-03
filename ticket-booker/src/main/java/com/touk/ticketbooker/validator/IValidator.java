package com.touk.ticketbooker.validator;

public interface IValidator<T> {

    boolean isValid(T obj);
    boolean isInvalid(T obj);
}
