package ru.kosstantin.contacts.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "PHONES")
public class Phone extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_ID")
    private Integer id;

    @Column(name = "PHONE_TYPE")
    private PhoneType type;

    @NotEmpty
    @Column(name = "PHONE_NUMBER")
    private String number;

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;

    @OneToOne(mappedBy = "basePhone", fetch = FetchType.EAGER)
    private Contact baseContact;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getBaseContact() {
        return baseContact;
    }

    public void setBaseContact(Contact baseContact) {
        this.baseContact = baseContact;
    }

    @Override
    public String toString() {
        return number;
    }
}
