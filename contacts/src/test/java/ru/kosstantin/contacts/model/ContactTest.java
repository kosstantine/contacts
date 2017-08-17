package ru.kosstantin.contacts.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

public class ContactTest {

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void fieldIsNull() {
        Contact contact = new Contact();

        Set<ConstraintViolation<Contact>> constraintViolations =
                validator.validate(contact);

        assertEquals(1, constraintViolations.size());
        assertEquals("не может быть пусто", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void emailIsValid() {
        Contact contact = new Contact();

        contact.setFirstName("Name");
        contact.setEmail("email");

        Set<ConstraintViolation<Contact>> constraintViolations =
                validator.validate(contact);

        assertEquals(1, constraintViolations.size());
        assertEquals("email определен в неверном формате",
                constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void contactIsValid() {
        Contact contact = new Contact();

        contact.setFirstName("Ivan");
        contact.setEmail("email@email.ru");

        Set<ConstraintViolation<Contact>> constraintViolations =
                validator.validate(contact);

        assertEquals(0, constraintViolations.size());
    }
}