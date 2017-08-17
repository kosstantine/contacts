package ru.kosstantin.contacts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kosstantin.contacts.model.Contact;
import ru.kosstantin.contacts.service.ContactService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAllContacts(ModelMap model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);

        return "contacts";
    }

    @RequestMapping(value = "/add-contact", method = RequestMethod.GET)
    public String getNewContact(ModelMap model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);

        return "add-contact";
    }

    @RequestMapping(value = "/add-contact", method = RequestMethod.POST)
    public String postNewContact(@Valid @ModelAttribute("contact") Contact contact,
                                 BindingResult result) {
        if (result.hasErrors()) return "add-contact";

        contactService.saveContact(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete-contact-{id}", method = RequestMethod.GET)
    public String getDeleteContact(@PathVariable Integer id) {
        contactService.deleteContact(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit-contact-{id}", method = RequestMethod.GET)
    public String getEditContact(@PathVariable Integer id,
                                 ModelMap model) {
        Contact contact = contactService.getContact(id);
        model.addAttribute("contact", contact);

        return "edit-contact";
    }

    @RequestMapping(value = "/edit-contact-{id}", method = RequestMethod.POST)
    public String postEditContact(@PathVariable Integer id,
                                  @Valid @ModelAttribute("contact") Contact contact,
                                  BindingResult result) {
        if (result.hasErrors()) return "edit-contact";

        contact.setPhones(contactService.getContact(id).getPhones());
        contact.setBasePhone(contactService.getContact(id).getBasePhone());
        contactService.saveContact(contact);

        return "redirect:/";
    }
}
