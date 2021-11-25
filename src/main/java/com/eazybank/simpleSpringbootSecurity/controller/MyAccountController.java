package com.eazybank.simpleSpringbootSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccountController {
    @GetMapping("/myAccount")
    public String getMyAccount(){
        return "my account";
    }
}
