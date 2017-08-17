package ru.kosstantin.contacts.service;

import ru.kosstantin.contacts.model.Phone;

import java.util.List;

public interface PhoneService {

    List<Phone> getPhones(Integer contactId);

    Phone getPhone(Integer id);

    void savePhone(Phone phone);

    void deletePhone(Integer id);
}
