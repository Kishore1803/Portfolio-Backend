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

    public ContactService(
            ContactRepository contactRepository,
            @Value("${resend.api.key}") String resendApiKey) {

        this.contactRepository = contactRepository;
        this.resend = new Resend(resendApiKey);
    }

    public Contact saveContact(Contact contact) {

        // Save contact to TiDB Cloud
        Contact savedContact = contactRepository.save(contact);

        try {

            // Create email request
            CreateEmailOptions email = CreateEmailOptions.builder()
                    .from("onboarding@resend.dev")
                    .to("kannan.v4704@gmail.com")
                    .subject("Portfolio Contact: " + contact.getSubject())
                    .html("<h2>New Portfolio Contact</h2>" +
                          "<p><strong>Name:</strong> " + contact.getName() + "</p>" +
                          "<p><strong>Email:</strong> " + contact.getEmail() + "</p>" +
                          "<p><strong>Subject:</strong> " + contact.getSubject() + "</p>" +
                          "<p><strong>Message:</strong></p>" +
                          "<p>" + contact.getMessage() + "</p>").build();

            // Send email through Resend
            resend.emails().send(email);
            System.out.println("Email sent successfully!");
            return savedContact;

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}