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
import ru.kosstantin.contacts.model.Phone;
import ru.kosstantin.contacts.model.PhoneType;
import ru.kosstantin.contacts.service.ContactService;
import ru.kosstantin.contacts.service.PhoneService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PhoneController {

    private ContactService contactService;
    private PhoneService phoneService;

    @Autowired
    public PhoneController(ContactService contactService, PhoneService phoneService) {
        this.contactService = contactService;
        this.phoneService = phoneService;
    }

    @RequestMapping(value = "/add-phone-{id}", method = RequestMethod.GET)
    public String getNewPhone(@PathVariable Integer id,
                              ModelMap model) {
        List<Phone> phones = phoneService.getPhones(id);
        model.addAttribute("phones", phones);

        Contact contact = contactService.getContact(id);
        model.addAttribute("contact", contact);

        Phone phone = new Phone();
        model.addAttribute("phone", phone);

        model.addAttribute("types", PhoneType.values());

        return "add-phone";
    }

    @RequestMapping(value = "/add-phone-{id}", method = RequestMethod.POST)
    public String postNewPhone(@PathVariable Integer id,
                               @ModelAttribute("contact") Contact contact,
                               @Valid @ModelAttribute("phone") Phone phone,
                               BindingResult result) {
        if (result.hasErrors()) return "add-phone";

        contact.addPhone(phone);
        phoneService.savePhone(phone);

        return "redirect:/show-phones-{id}";
    }

    @RequestMapping(value = "/show-phones-{id}", method = RequestMethod.GET)
    public String getShowPhones(@PathVariable Integer id,
                                ModelMap model) {
        List<Phone> phones = phoneService.getPhones(id);
        model.addAttribute("phones", phones);

        Contact contact = contactService.getContact(id);
        model.addAttribute("contact", contact);

        return "show-phones";
    }

    @RequestMapping(value = "/set-base-{contactId}-{phoneId}", method = RequestMethod.GET)
    public String postSetBasePhone(@PathVariable Integer contactId,
                                   @PathVariable Integer phoneId) {
        Contact contact = contactService.getContact(contactId);
        contact.setBasePhone(phoneService.getPhone(phoneId));
        contactService.saveContact(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/delete-phone-{contactId}-{phoneId}", method = RequestMethod.GET)
    public String getDeletePhone(@PathVariable Integer contactId,
                                 @PathVariable Integer phoneId) {
        Contact contact = contactService.getContact(contactId);

        if(contact.getBasePhone().equals(phoneService.getPhone(phoneId))) {
            contact.setBasePhone(null);
        }

        contact.removePhone(phoneService.getPhone(phoneId));
        phoneService.deletePhone(phoneId);
        contactService.saveContact(contact);

        return "redirect:/show-phones-{contactId}";
    }

    @RequestMapping(value = "/edit-phone-{contactId}-{phoneId}", method = RequestMethod.GET)
    public String getEditPhone(@PathVariable Integer contactId,
                               @PathVariable Integer phoneId,
                               ModelMap model) {
        Phone phone = phoneService.getPhone(phoneId);
        model.addAttribute("phone", phone);

        model.addAttribute("types", PhoneType.values());

        Contact contact = contactService.getContact(contactId);
        model.addAttribute("contact", contact);

        return "edit-phone";
    }

    @RequestMapping(value = "/edit-phone-{contactId}-{phoneId}", method = RequestMethod.POST)
    public String postEditPhone(@PathVariable Integer contactId,
                                @PathVariable Integer phoneId,
                                @Valid @ModelAttribute("phone") Phone phone,
                                BindingResult result) {
        if (result.hasErrors()) return "edit-phone";

        phone.setId(phoneId);
        phone.setContact(contactService.getContact(contactId));
        phoneService.savePhone(phone);

        return "redirect:/show-phones-{contactId}";
    }
}
