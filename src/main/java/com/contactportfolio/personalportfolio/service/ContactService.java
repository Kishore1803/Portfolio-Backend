package com.contactportfolio.personalportfolio.service;

import com.contactportfolio.personalportfolio.dto.ContactRequest;
import com.contactportfolio.personalportfolio.dto.ContactResponse;

public interface ContactService {
    ContactResponse saveContact(ContactRequest request);
}