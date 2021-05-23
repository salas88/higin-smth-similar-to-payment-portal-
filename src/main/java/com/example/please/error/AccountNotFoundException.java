package com.example.please.error;

import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

public class AccountNotFoundException extends RuntimeException{


    public AccountNotFoundException(Integer id){
        super(String.format("Account with Id %d not found", id));
    }


}