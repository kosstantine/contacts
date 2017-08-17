package ru.kosstantin.contacts.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.kosstantin.contacts.config.AppConfig;
import ru.kosstantin.contacts.config.WebConfig;
import ru.kosstantin.contacts.model.Contact;
import ru.kosstantin.contacts.service.ContactService;
import ru.kosstantin.contacts.service.PhoneService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
public abstract class AbstractControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public ContactService getContactService() {
        return contactService;
    }

    public PhoneService getPhoneService() {
        return phoneService;
    }

    public Contact getNewContact() {
        Contact contact = new Contact();
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.setEmail("email@email.ru");

        return contact;
    }

    List<Contact> getAllContacts() {
        return getContactService().getAllContacts();
    }

    Contact getLastContact() {
        List<Contact> contacts = getAllContacts();

        return contacts.get(contacts.size() - 1);
    }
}
