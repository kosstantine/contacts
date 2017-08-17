package ru.kosstantin.contacts.dao;

import org.springframework.stereotype.Repository;
import ru.kosstantin.contacts.model.Contact;
import ru.kosstantin.contacts.model.Phone;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("phoneDao")
public class PhoneDaoImpl extends AbstractDao<Phone> implements PhoneDao {

    @Override
    public List<Phone> getPhones(Integer contactId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Phone> query = builder.createQuery(Phone.class);
        Root<Contact> root = query.from(Contact.class);
        Join<Contact, Phone> phones = root.join("phones");
        query.select(phones).where(builder.equal(root.get("id"), contactId));

        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public Phone getPhone(Integer id) {
        return get(id);
    }

    @Override
    public void savePhone(Phone phone) {
        save(phone);
    }

    @Override
    public void deletePhone(Integer id) {
        delete(id);
    }
}
