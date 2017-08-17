package ru.kosstantin.contacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kosstantin.contacts.dao.PhoneDao;
import ru.kosstantin.contacts.model.Phone;

import java.util.List;

@Service("phoneService")
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private PhoneDao phoneDao;

    @Autowired
    public PhoneServiceImpl(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    @Override
    public List<Phone> getPhones(Integer contactId) {
        return phoneDao.getPhones(contactId);
    }

    @Override
    public Phone getPhone(Integer id) {
        return phoneDao.getPhone(id);
    }

    @Override
    public void savePhone(Phone phone) {
        phoneDao.savePhone(phone);
    }

    @Override
    public void deletePhone(Integer id) {
        phoneDao.deletePhone(id);
    }
}
