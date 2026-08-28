package com.contactportfolio.personalportfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.contactportfolio.personalportfolio.entity.Contact;
import com.contactportfolio.personalportfolio.repository.ContactRepository;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final Resend resend;

    public ContactService(ContactRepository contactRepository, @Value("${resend.api.key}") String resendApiKey) {

        this.contactRepository = contactRepository;
        this.resend = new Resend(resendApiKey);
    }

    public Contact saveContact(Contact contact) {

        // Save contact to TiDB/MySQL
        Contact savedContact = contactRepository.save(contact);

        try {

            // Send email using Resend
            CreateEmailOptions email = CreateEmailOptions.builder()
                    .from("onboarding@resend.dev")
                    .to("kannan.v4704@gmail.com")
                    .subject("Portfolio Contact: " + contact.getSubject())
                    .html("<h2>New Portfolio Contact</h2>" +
                          "<p><strong>Name:</strong> " + contact.getName() + "</p>" +
                          "<p><strong>Email:</strong> " + contact.getEmail() + "</p>" +
                          "<p><strong>Subject:</strong> " + contact.getSubject() + "</p>" +
                          "<p><strong>Message:</strong></p>" +
                          "<p>" + contact.getMessage() + "</p>")
                   .build();

            resend.emails().send(email);

            return savedContact;

        } catch (Exception e) {

            // Email failed → remove database record
            contactRepository.delete(savedContact);

            throw new RuntimeException("Failed to send email", e);
        }
    }
}