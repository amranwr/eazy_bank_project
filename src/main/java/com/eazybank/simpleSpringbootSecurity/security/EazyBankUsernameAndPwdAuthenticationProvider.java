package com.eazybank.simpleSpringbootSecurity.security;

import com.eazybank.simpleSpringbootSecurity.models.Authority;
import com.eazybank.simpleSpringbootSecurity.models.Customer;
import com.eazybank.simpleSpringbootSecurity.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EazyBankUsernameAndPwdAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        System.out.println(username);
        String pwd = authentication.getCredentials().toString();
        List<Customer> customers = customerRepo.findByEmail(username);
        for(Authority authority: customers.get(0).getAuthorities()) System.out.println(authority.getName());
        if(customers.size() ==0 ){
            if(passwordEncoder.matches(pwd,customers.get(0).getPwd())){
                return new UsernamePasswordAuthenticationToken(username,pwd,getAuthorites(customers.get(0).getAuthorities()));
            }
            throw new BadCredentialsException("invalid password");
        }else{
            throw new BadCredentialsException("No user registerd with this details");
        }

    }

    private Collection<? extends GrantedAuthority> getAuthorites(Set<Authority> authorities) {
        List<GrantedAuthority> mylist = new ArrayList<>();
        for(Authority authority : authorities){
            mylist.add(new SimpleGrantedAuthority(authority.getName()));
            System.out.println(authority.getName());
        }
        return mylist;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
