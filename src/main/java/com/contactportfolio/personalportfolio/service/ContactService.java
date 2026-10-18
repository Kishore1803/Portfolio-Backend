package com.contactportfolio.personalportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contactportfolio.personalportfolio.entity.Contact;
import com.contactportfolio.personalportfolio.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }
}