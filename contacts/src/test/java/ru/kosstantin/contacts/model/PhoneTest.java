package ru.kosstantin.contacts.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.Assert.*;

public class PhoneTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void fieldIsNull() {
        Phone phone = new Phone();

        Set<ConstraintViolation<Phone>> constraintViolations =
                validator.validate(phone);

        assertEquals(1, constraintViolations.size());
        assertEquals("не может быть пусто", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void phoneIsValid() {
        Phone phone = new Phone();

        phone.setNumber("8-800-2000-600");

        Set<ConstraintViolation<Phone>> constraintViolations =
                validator.validate(phone);

        assertEquals(0, constraintViolations.size());
    }
}