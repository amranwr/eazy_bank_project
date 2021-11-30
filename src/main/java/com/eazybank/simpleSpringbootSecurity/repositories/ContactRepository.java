package com.eazybank.simpleSpringbootSecurity.repositories;

import com.eazybank.simpleSpringbootSecurity.models.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
