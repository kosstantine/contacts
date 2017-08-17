package ru.kosstantin.contacts.controller;

import org.junit.Test;
import ru.kosstantin.contacts.model.Contact;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerTest extends AbstractControllerTest {

    private Integer getSizeOfContactList() {
        List<Contact> contacts = getAllContacts();
        return contacts.size();
    }

    private Contact getContactById(Integer id) {
        return getContactService().getContact(id);
    }

    @Test
    public void testHome() throws Exception {
        getMockMvc().perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/contacts.jsp"));
    }

    @Test
    public void testAddContact() throws Exception {
        int sizeBeforeSave = getSizeOfContactList();

        getMockMvc().perform(post("/add-contact")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("email", "email@email.ru"))
                .andExpect(redirectedUrl("/"));

        int size = getSizeOfContactList();

        assert size == sizeBeforeSave + 1;

        Contact contact = getLastContact();

        assert contact.getFirstName().equals("firstName");
        assert contact.getLastName().equals("lastName");
        assert contact.getEmail().equals("email@email.ru");
    }

    @Test
    public void testDeleteContact() throws Exception {
        Contact contact = getNewContact();

        int sizeBeforeSave = getSizeOfContactList();

        getContactService().saveContact(contact);

        Contact foundContact = getLastContact();
        int id = foundContact.getId();
        int size = getSizeOfContactList();

        assert size == sizeBeforeSave + 1;
        assert getContactById(id).getFirstName().equals("firstName");

        getMockMvc().perform(get("/delete-contact-" + id))
                .andExpect(redirectedUrl("/"));

        int updatedSize = getSizeOfContactList();

        assert size == updatedSize + 1;
        assert getContactById(id) == null;
    }

    @Test
    public void testEditContact() throws Exception {
        Contact contact = getNewContact();

        getContactService().saveContact(contact);

        Contact foundContact = getLastContact();
        int id = foundContact.getId();
        int size = getSizeOfContactList();

        getMockMvc().perform(post("/edit-contact-" + id)
                .param("firstName", "newFirstName")
                .param("lastName", "newLastName")
                .param("email", "newEmail@email.ru"))
                .andExpect(redirectedUrl("/"));

        int updatedSize = getSizeOfContactList();

        assert size == updatedSize;
        assert getContactById(id).getFirstName().equals("newFirstName");
        assert getContactById(id).getLastName().equals("newLastName");
        assert getContactById(id).getEmail().equals("newEmail@email.ru");
    }
}