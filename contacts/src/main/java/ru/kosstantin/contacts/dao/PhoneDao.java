package ru.kosstantin.contacts.dao;

import ru.kosstantin.contacts.model.Phone;

import java.util.List;

public interface PhoneDao {

    List<Phone> getPhones(Integer contactId);

    Phone getPhone(Integer id);

    void savePhone(Phone phone);

    void deletePhone(Integer id);
}
