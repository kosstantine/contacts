package ru.kosstantin.contacts.service;

import ru.kosstantin.contacts.model.Contact;
import ru.kosstantin.contacts.model.Phone;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContact(Integer id);

    void saveContact(Contact contact);

    void deleteContact(Integer id);
}
