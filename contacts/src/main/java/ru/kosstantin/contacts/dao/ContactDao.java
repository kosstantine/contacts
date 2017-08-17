package ru.kosstantin.contacts.dao;

import ru.kosstantin.contacts.model.Contact;
import ru.kosstantin.contacts.model.Phone;

import java.util.List;

public interface ContactDao {

    List<Contact> getAllContacts();

    Contact getContact(Integer id);

    void saveContact(Contact contact);

    void deleteContact(Integer id);
}
