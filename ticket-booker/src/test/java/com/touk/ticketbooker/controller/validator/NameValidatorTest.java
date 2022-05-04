package com.touk.ticketbooker.controller.validator;

import com.touk.ticketbooker.validator.IValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameValidatorTest {

    private final IValidator<String> validator = new NameValidator(3);

    @Test
    void isNameValidTest() {
        final String validName1 = "Nikodem";
        final String validName2 = "Łukasz";
        final String invalidName1 = "Bo";
        final String invalidName2 = "bobo";
        final String invalidName3 = "Huba-buba";

        assertTrue(validator.isValid(validName1));
        assertTrue(validator.isValid(validName2));
        assertFalse(validator.isValid(invalidName1));
        assertFalse(validator.isValid(invalidName2));
        assertFalse(validator.isValid(invalidName3));
    }

    @Test
    void isNameInvalidTest() {
        final String validName1 = "Nikodem";
        final String validName2 = "Łukasz";
        final String invalidName1 = "Bo";
        final String invalidName2 = "bobo";
        final String invalidName3 = "Huba-buba";

        assertFalse(validator.isInvalid(validName1));
        assertFalse(validator.isInvalid(validName2));
        assertTrue(validator.isInvalid(invalidName1));
        assertTrue(validator.isInvalid(invalidName2));
        assertTrue(validator.isInvalid(invalidName3));
    }
}