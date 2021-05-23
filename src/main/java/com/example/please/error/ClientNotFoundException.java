package com.example.please.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(Integer id){
        super(String.format("Client with Id %d not found", id));
    }

}
