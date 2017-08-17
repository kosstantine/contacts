package ru.kosstantin.contacts.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "CONTACTS")
public class Contact extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_ID")
    private Integer id;

    @NotEmpty
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Phone> phones = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "PHONE_ID")
    private Phone basePhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Phone getBasePhone() {
        return basePhone;
    }

    public void setBasePhone(Phone basePhone) {
        this.basePhone = basePhone;
    }

    public void addPhone(Phone phone) {
        phone.setContact(this);
        phones.add(phone);
    }

    public void removePhone(Phone phone) {
        getPhones().remove(phone);
    }
}
