package ru.kosstantin.contacts.model;

import java.io.Serializable;

public interface Entity<ID extends Serializable> {

    ID getId();
}
