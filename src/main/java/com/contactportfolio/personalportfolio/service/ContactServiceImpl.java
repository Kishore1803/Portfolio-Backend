package com.contactportfolio.personalportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.contactportfolio.personalportfolio.dto.ContactRequest;
import com.contactportfolio.personalportfolio.dto.ContactResponse;
import com.contactportfolio.personalportfolio.entity.Contact;
import com.contactportfolio.personalportfolio.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ContactResponse saveContact(ContactRequest request) {

        Contact contact = new Contact();

        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setSubject(request.getSubject());
        contact.setMessage(request.getMessage());

        // Save contact details in MySQL
        contactRepository.save(contact);

        // Send email
        emailService.sendEmail(contact);

        // Return response
        return new ContactResponse(
                "SUCCESS",
                "Message sent successfully!"
        );
    }
}