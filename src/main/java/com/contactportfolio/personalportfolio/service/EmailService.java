package com.contactportfolio.personalportfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.contactportfolio.personalportfolio.entity.Contact;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Contact contact) {

        SimpleMailMessage message = new SimpleMailMessage();

        // Your email address
        message.setTo("kannan.v4704@gmail.com");

        // Email subject
        message.setSubject("New Portfolio Contact Message");

        // Email body
        message.setText(
                "New Contact Form Submission from your Portfolio\n\n" +
                "Name : " + contact.getName() + "\n" +
                "Email : " + contact.getEmail() + "\n" +
                "Subject : " + contact.getSubject() + "\n\n" +
                "Message:\n" +
                contact.getMessage()
        );

        mailSender.send(message);
    }
}