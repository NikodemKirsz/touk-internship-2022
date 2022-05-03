package com.touk.ticketbooker.controller.validator;

import com.touk.ticketbooker.validator.IValidator;
import org.springframework.stereotype.Component;

import static com.touk.ticketbooker.Multiplex.*;

@Component
public class NameValidator implements IValidator<String> {

    private final int minNameLength;

    public NameValidator() {
        this(MIN_NAME_LENGTH);
    }

    public NameValidator(int minNameLength) {
        this.minNameLength = minNameLength;
    }

    @Override
    public boolean isValid(String name) {
        if (name == null) return false;

        int length = name.length();
        if (name.length() < minNameLength) return false;

        if(!Character.isUpperCase(name.charAt(0))) return false;
        for (int i = 1; i < length; i++) {
            if (!Character.isLowerCase(name.charAt(i))) return false;
        }

        return true;
    }

    @Override
    public boolean isInvalid(String name) {
        return !isValid(name);
    }
}
