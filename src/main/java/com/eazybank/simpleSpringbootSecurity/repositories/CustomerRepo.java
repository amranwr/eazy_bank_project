package com.eazybank.simpleSpringbootSecurity.repositories;

import com.eazybank.simpleSpringbootSecurity.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer,Long> {
    List<Customer> findByEmail(String email);
}
