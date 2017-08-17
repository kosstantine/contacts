package ru.kosstantin.contacts.dao;

import org.springframework.stereotype.Repository;
import ru.kosstantin.contacts.model.Contact;

import javax.persistence.criteria.*;
import java.util.List;

@Repository("contactDao")
public class ContactDaoImpl extends AbstractDao<Contact> implements ContactDao {

    @Override
    public List<Contact> getAllContacts() {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Contact> query = builder.createQuery(Contact.class);
        Root<Contact> root = query.from(Contact.class);
        query.select(root).orderBy(builder.asc(root.get("id")));

        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public Contact getContact(Integer id) {
        return get(id);
    }

    @Override
    public void saveContact(Contact contact) {
        save(contact);
    }

    @Override
    public void deleteContact(Integer id) {
        delete(id);
    }
}
