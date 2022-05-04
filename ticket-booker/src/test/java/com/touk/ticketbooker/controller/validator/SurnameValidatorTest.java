package com.touk.ticketbooker.controller.validator;

import com.touk.ticketbooker.validator.IValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurnameValidatorTest {

    private final IValidator<String> validator = new SurnameValidator(3, "-");


    @Test
    void isSurnameValidTest() {
        final String validSurname1 = "Kirsz";
        final String validSurname2 = "Świątek-Piątek";
        final String invalidSurname1 = "Bo";
        final String invalidSurname2 = "bobo";
        final String invalidSurname3 = "Huba-buba";
        final String invalidSurname4 = "Huba-";

        assertTrue(validator.isValid(validSurname1));
        assertTrue(validator.isValid(validSurname2));
        assertFalse(validator.isValid(invalidSurname1));
        assertFalse(validator.isValid(invalidSurname2));
        assertFalse(validator.isValid(invalidSurname3));
        assertFalse(validator.isValid(invalidSurname4));
    }

    @Test
    void isSurnameInvalidTest() {
        final String validSurname1 = "Nikodem";
        final String validSurname2 = "Świątek-Piątek";
        final String invalidSurname1 = "Bo";
        final String invalidSurname2 = "bobo";
        final String invalidSurname3 = "Huba-buba";
        final String invalidSurname4 = "Huba_buba";

        assertFalse(validator.isInvalid(validSurname1));
        assertFalse(validator.isInvalid(validSurname2));
        assertTrue(validator.isInvalid(invalidSurname1));
        assertTrue(validator.isInvalid(invalidSurname2));
        assertTrue(validator.isInvalid(invalidSurname3));
        assertTrue(validator.isInvalid(invalidSurname4));
    }
}