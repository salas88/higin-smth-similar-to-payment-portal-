package com.example.please.error;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }


}
