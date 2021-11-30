package com.eazybank.simpleSpringbootSecurity.controller;


import java.security.Principal;
import java.util.List;

import com.eazybank.simpleSpringbootSecurity.models.Customer;
import com.eazybank.simpleSpringbootSecurity.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

	@Autowired
	private CustomerRepo customerRepository;
	
	@RequestMapping("/user")
	public Customer getUserDetailsAfterLogin(Principal user) {
		List<Customer> customers = customerRepository.findByEmail(user.getName());
		if (customers.size() > 0) {
			return customers.get(0);
		}else {
			return null;
		}
		
	}

}
