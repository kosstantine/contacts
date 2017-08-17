package ru.kosstantin.contacts.model;

import java.io.Serializable;

public abstract class AbstractEntity<ID extends Serializable> implements Entity<ID> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity entity = (AbstractEntity) o;

        return getId() != null ? getId().equals(entity.getId()) : entity.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
