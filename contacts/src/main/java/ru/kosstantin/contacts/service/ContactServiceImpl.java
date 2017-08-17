package ru.kosstantin.contacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kosstantin.contacts.dao.ContactDao;
import ru.kosstantin.contacts.model.Contact;

import java.util.List;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @Override
    public Contact getContact(Integer id) {
        return contactDao.getContact(id);
    }

    @Override
    public void saveContact(Contact contact) {
        contactDao.saveContact(contact);
    }

    @Override
    public void deleteContact(Integer id) {
        contactDao.deleteContact(id);
    }
}
