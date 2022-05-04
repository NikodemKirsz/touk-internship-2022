package com.touk.ticketbooker.controller.validator;

import com.touk.ticketbooker.validator.IValidator;
import org.springframework.stereotype.Component;

import static com.touk.ticketbooker.Multiplex.*;

@Component
public class SurnameValidator implements IValidator<String> {

    private final int minSurnamePartLength;
    private final String surnameSeparator;

    public SurnameValidator() {
        this(MIN_SURNAME_PART_LENGTH, SURNAME_SEPARATOR);
    }

    public SurnameValidator(int minSurnamePartLength, String surnameSeparator) {
        this.minSurnamePartLength = minSurnamePartLength;
        this.surnameSeparator = surnameSeparator;
    }

    @Override
    public boolean isValid(String surname) {
        if (surname == null
                || surname.length() < minSurnamePartLength
                || surname.endsWith(surnameSeparator)) return false;

        String[] parts = surname.split(surnameSeparator);
        if (parts.length > 2) return false;

        for (var part : parts) {
            int length = part.length();
            if (length < minSurnamePartLength) return false;

            if (!Character.isUpperCase(part.charAt(0))) return false;
            for (int i = 1; i < length; i++) {
                if (!Character.isLowerCase(part.charAt(i))) return false;
            }
        }

        return true;
    }

    @Override
    public boolean isInvalid(String surname) {
        return !isValid(surname);
    }
}
