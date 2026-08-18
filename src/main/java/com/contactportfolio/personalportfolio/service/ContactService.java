package com.contactportfolio.personalportfolio.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.contactportfolio.personalportfolio.entity.Contact;
import com.contactportfolio.personalportfolio.repository.ContactRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final JavaMailSender mailSender;

    public ContactService(
            ContactRepository contactRepository,
            JavaMailSender mailSender) {

        this.contactRepository = contactRepository;
        this.mailSender = mailSender;
    }

    public Contact saveContact(Contact contact) {

        // Save contact to MySQL
        Contact savedContact = contactRepository.save(contact);

        // Send email
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo("kannan.v4704@gmail.com");

        mailMessage.setSubject(
                "Portfolio Contact: " + contact.getSubject()
        );

        mailMessage.setText(
                "You received a new message from your portfolio.\n\n" +
                "Name: " + contact.getName() + "\n" +
                "Email: " + contact.getEmail() + "\n" +
                "Subject: " + contact.getSubject() + "\n\n" +
                "Message:\n" +
                contact.getMessage()
        );

        mailSender.send(mailMessage);

        return savedContact;
    }
}