package ru.kosstantin.contacts.dao;

import ru.kosstantin.contacts.model.Entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

abstract class AbstractDao<T extends Entity> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    AbstractDao() {
        entityClass = (Class<T>) ((ParameterizedType)this
                .getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    void save(T entity) {
        entityManager.merge(entity);
    }

    T get(Integer id) {
        return entityManager.find(entityClass, id);
    }

    void delete(Integer id) {
        entityManager.remove(get(id));
    }

    EntityManager getEntityManager() {                           //find another solution
        return entityManager;
    }
}
