package ru.kosstantin.contacts.controller;

import org.junit.Test;
import ru.kosstantin.contacts.model.Contact;
import ru.kosstantin.contacts.model.Phone;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

public class PhoneControllerTest extends AbstractControllerTest {

    private List<Phone> getAllPhones(Integer contactId) {
        return getPhoneService().getPhones(contactId);
    }

    private Integer getSizeOfPhoneList(Integer contactId) {
        return getAllPhones(contactId).size();
    }

    private Phone getLastPhone(Integer contactId) {
        List<Phone> phones = getAllPhones(contactId);

        return phones.get(phones.size() - 1);
    }

    @Test
    public void testShowPhones() throws Exception {
        Contact contact = getNewContact();
        getContactService().saveContact(contact);

        int id = getLastContact().getId();

        getMockMvc().perform(get("/show-phones-" + id))
                .andExpect(forwardedUrl("/WEB-INF/views/show-phones.jsp"));
    }

    @Test
    public void testAddPhone() throws Exception {
        Contact contact = getNewContact();
        getContactService().saveContact(contact);

        int contactId = getLastContact().getId();

        int sizeBeforeSave = getSizeOfPhoneList(contactId);

        getMockMvc().perform(post("/add-phone-" + contactId)
                .param("number", "number"));

        int size = getSizeOfPhoneList(contactId);

        assert size == sizeBeforeSave + 1;
        assert getLastPhone(contactId).getNumber().equals("number");
    }
}