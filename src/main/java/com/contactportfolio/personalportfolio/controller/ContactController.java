package com.contactportfolio.personalportfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactportfolio.personalportfolio.dto.ContactRequest;
import com.contactportfolio.personalportfolio.dto.ContactResponse;
import com.contactportfolio.personalportfolio.service.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
@Validated
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponse> saveContact(
            @Valid @RequestBody ContactRequest request) {

        ContactResponse response = contactService.saveContact(request);

        return ResponseEntity.ok(response);
    }
}