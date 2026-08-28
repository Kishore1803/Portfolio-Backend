package com.contactportfolio.personalportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.contactportfolio.personalportfolio.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}