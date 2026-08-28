package com.contactportfolio.personalportfolio.service;

import org.springframework.stereotype.Service;

import com.contactportfolio.personalportfolio.entity.Contact;
import com.contactportfolio.personalportfolio.repository.ContactRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact saveContact(Contact contact) {

        return contactRepository.save(contact);
    }
}