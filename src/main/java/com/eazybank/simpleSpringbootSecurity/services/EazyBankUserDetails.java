package com.eazybank.simpleSpringbootSecurity.services;

import com.eazybank.simpleSpringbootSecurity.models.Customer;
import com.eazybank.simpleSpringbootSecurity.repositories.CustomerRepo;
import com.eazybank.simpleSpringbootSecurity.models.SecurityCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EazyBankUserDetails implements UserDetailsService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = this.customerRepo.findByEmail(username);
        if(customers.size() == 0){
            throw new UsernameNotFoundException("user not found with that email : "+ username);
        }
        return new SecurityCustomer(customers.get(0));
    }
}
